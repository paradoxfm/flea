-- настройка полнотекстового поиска
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

create trigger trg_upd_ins_advert
before insert or update or delete on fle.fl_advert for each row execute procedure fle.update_full_text_advert();


create or replace function fle.update_full_text_advert() returns trigger as $$
declare
	mstr varchar(30);
begin
	new.tsv := to_tsvector(new.full_text);
	return new;
end;
$$ language plpgsql;
