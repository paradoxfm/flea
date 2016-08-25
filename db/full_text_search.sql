-- создаем расширение для поиска по триграммам
create extension pg_trgm;

-- настройка полнотекстового поиска
-- словари из папки dict надо скопировать в C:/Program Files (x86)/PostgreSQL/9.5/share/tsearch_data/
-- stop words там уже есть, но на всякий случай в папке есть для русского
create text search dictionary ispell_ru (
	template  =   ispell,
	dictfile  =   ru,
	afffile   =   ru,
	stopwords =   russian
);

create text search dictionary ispell_en (
	template  = ispell,
	dictfile  = en,
	afffile   = en,
	stopwords = english
);

create text search configuration ru ( copy = russian );

alter text search configuration ru alter mapping
for word, hword, hword_part with ispell_ru, russian_stem;

alter text search configuration ru alter mapping
for asciiword, asciihword, hword_asciipart with ispell_en, english_stem;

--set default_text_search_config = 'ru';
-- в postgresql.conf default_text_search_config = 'ru'
-- чтоб проверить надо выполнить select * from ts_debug('ru', 'Search. Поиск. 152');
-- должно быть 2 словаря
