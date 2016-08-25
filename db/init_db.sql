create database flea with owner = postgres
encoding = 'UTF8'
tablespace = pg_default
lc_collate = 'Russian_Russia.1251'
lc_ctype = 'Russian_Russia.1251'
connection limit = -1;

--create schema fle;
--set search_path = fle;--выбор схемы по умолчанию

-- расширение для case ignore строк
create extension citext;

-- таблица для хранения сессии пользователей
create table fl_persistent_login (
	series character varying(64) primary key,
	last_used timestamp without time zone,
	token character varying(64),
	username character varying(64)
);
create unique index fl_persistent_login_id_idx on fl_persistent_login (series);

-- таблица пользователей системы
create table fl_users (
	id bigserial primary key,
	email citext not null unique,
	login character varying(14) not null,
	modification_time timestamp without time zone,
	name character varying(150),
	password_hash character varying(255) not null,
	user_prone_num character varying(10),
	register_date timestamp without time zone not null
);
create unique index fl_users_id_idx on fl_users (id);
create unique index fl_users_email_idx on fl_users (email);
create unique index fl_users_login_idx on fl_users (login);

-- таблица для привязки ролей к пользователям
-- todo: есть мнение что нужны роли и разрешения для ролей
create table fl_user_roles (
	user_id bigint not null references fl_users on delete cascade,
	rolename character varying(255) not null,
	constraint user_roles_pkey primary key (user_id, rolename)
);
create index fl_user_roles_user_id_idx on fl_user_roles (user_id);

-- категории объявлений
create table fl_advert_cat (
	id bigserial primary key,
	parent_id bigint references fl_advert_cat on delete cascade,
	end_cat boolean,-- конечная категория, те та с которая идет в объявление
	title character varying(100)
);
create unique index fl_advert_cat_id_idx on fl_advert_cat (id);
create index fl_advert_parent_id_idx on fl_advert_cat (parent_id);

create table fl_advert_cat_tree (
	ancestor_id bigint references fl_advert_cat,
	descendant_id bigint references fl_advert_cat,
	level smallint check (level > 0),
	"order" smallint
);
create index fl_advert_cat_tree_ancestor_idx on fl_advert_cat_tree (ancestor_id);
create index fl_advert_cat_tree_descendant_idx on fl_advert_cat_tree (descendant_id);

-- таблица объявлений
create table fl_advert (
	id bigserial primary key,
	cat_id bigint references fl_advert_cat on delete cascade,
	user_id bigint not null references fl_users on delete cascade,
	title character varying(300),
	full_text text,
	keywords character varying(500),
	tsv tsvector
);
create unique index fl_advert_id_idx on fl_advert (id);
create index fl_advert_fts_idx on fl_advert using gin(tsv);
create index fl_advert_fk_cat_id_idx on fl_advert (id);
create index fl_advert_user_id_idx on fl_advert (id);

create table fl_address (
	id bigserial primary key,
	type smallint,
	zip character varying(20),
	kladr character varying(60),
	fias character varying(100),
	country character varying(100),
	region character varying(150),
	area character varying(150),
	city character varying(80),
	nas_punkt character varying(80),
	street character varying(80),
	building smallint,
	korp character varying(10),
	build character varying(10),
	flat smallint,
	comment text
);
create unique index fl_address_id_idx on fl_address (id);
