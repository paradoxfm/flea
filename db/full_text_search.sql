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

/*create text search CONFIGURATION test_russian (COPY = 'russian');

CREATE TEXT SEARCH DICTIONARY russian_simple (
TEMPLATE = pg_catalog.simple,
	STOPWORDS = russian
);

CREATE TEXT SEARCH DICTIONARY russian_snowball (
	TEMPLATE = snowball,
	Language = russian,
	StopWords = russian
);
*/

create or replace function update_full_text_advert() returns trigger as $$
begin
	if (tg_op = 'INSERT' or (tg_op = 'UPDATE' and (old.title <> new.title or old.keywords <> new.keywords or old.full_text <> new.full_text))) then
		new.tsv = setweight(coalesce(to_tsvector('ru', new.title),''), 'A') || ' ' ||
							setweight(coalesce(to_tsvector('ru', new.keywords),''), 'B') || ' ' ||
							setweight(coalesce(to_tsvector('ru', new.full_text),''), 'D');
	end if;
	return new;
end;
$$ language plpgsql;

create trigger trg_upd_ins_advert
before insert or update on fl_advert for each row execute procedure update_full_text_advert();

