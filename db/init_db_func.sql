--триггер для создания дерева по паттерну closure table
create or replace function fl_advert_cat_ins() returns trigger as $$
begin
	insert into fl_advert_cat_tree (ancestor_id, descendant_id, level)
		select ancestor_id, new.id, level + 1-- уровень родительского эелмента относительно корня
		from fl_advert_cat_tree
		where new.parent_id is not null and descendant_id = new.parent_id-- root сюда не попадает
		union all
		select new.id, new.id, 0;-- добавляем ссылку на самого себя

	return new;
end;
$$ language plpgsql;

create trigger trg_ins_category_closure before insert on fl_advert_cat for each row execute procedure fl_advert_cat_ins();

-- триггер для обновления полнотекстового поиска
create or replace function fl_advert_ins_upd() returns trigger as $$
begin
	if (tg_op = 'INSERT' or (tg_op = 'UPDATE' and (old.title <> new.title or old.keywords <> new.keywords or old.full_text <> new.full_text))) then
		new.tsv = setweight(coalesce(to_tsvector('ru', new.title),''), 'A') || ' ' ||
				  setweight(coalesce(to_tsvector('ru', new.keywords),''), 'B') || ' ' ||
				  setweight(coalesce(to_tsvector('ru', new.full_text),''), 'D');
	end if;
	return new;
end;
$$ language plpgsql;

create trigger trg_upd_ins_advert before insert or update on fl_advert for each row execute procedure fl_advert_ins_upd();
