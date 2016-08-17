create database flea with owner = postgres
encoding = 'UTF8'
tablespace = pg_default
lc_collate = 'Russian_Russia.1251'
lc_ctype = 'Russian_Russia.1251'
connection limit = -1;

--create schema fle;
--set search_path = fle;--выбор схемы по умолчанию

-- таблица для хранения сессии пользователей
create table fl_persistent_login (
	series character varying(64) not null,
	last_used timestamp without time zone,
	token character varying(64),
	username character varying(64),
	constraint persistent_login_pkey primary key (series)
);

-- таблица пользователей системы
create table fl_users (
	id bigserial,
	email character varying(100) not null,
	login character varying(14) not null,
	modification_time timestamp without time zone,
	name character varying(150),
	password_hash character varying(255) not null,
	user_prone_num character varying(10),
	register_date timestamp without time zone not null,
	constraint users_pkey primary key (id)
);

-- таблица для привязки ролей к пользователям
-- todo: есть мнение что нужны роли и разрешения для ролей
create table fl_user_roles (
	user_id bigint not null,
	rolename character varying(255) not null,
	constraint user_roles_pkey primary key (user_id, rolename),
	constraint user_fk_user_roles foreign key (user_id) references fl_users (id) match simple on delete cascade
);

-- категории объявлений
create table fl_advert_cat (
	id bigserial,
	end_cat boolean,-- конечная категория, те та с которая идет в объявление
	title character varying(100),
	constraint fl_advert_cat_pkey primary key (id)
);

create table fl_advert_cat_tree (
	ancestor_id bigint,
	descendant_id bigint,
	level smallint,
	"order" smallint
);

-- таблица объявлений
create table fl_advert (
	id bigserial,
	cat_id bigint,
	user_id bigint not null,
	title character varying(300),
	full_text text,
	keywords character varying(500),
	tsv tsvector,
	constraint advert_pkey primary key (id),
	constraint fk_advert_owner foreign key (user_id) references fl_users (id) match simple on delete cascade,
	constraint fk_advert_cat foreign key (cat_id) references fl_advert_cat (id) match simple on delete cascade
);
create index fl_advert_fts_idx on fl_advert using gin(tsv);

create table fl_address (
	id bigserial,
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
	comment text,
	constraint fl_address_pkey primary key (id)
);
