create schema `cs230-pz` collate utf8mb4_general_ci;

create table kupac
(
	kupac_id int auto_increment
		primary key,
	naziv varchar(64) not null,
	kontakt_telefon varchar(64) null
);

create table porudzbina
(
	PORUDZBINA_ID int auto_increment
		primary key,
	IZNOS float not null,
	ADRESA_DOSTAVE varchar(128) not null,
	DATUM timestamp default current_timestamp() not null on update current_timestamp(),
	STATUS varchar(64) not null,
	kupac_id int not null,
	constraint porudzbina___fk_kupac
		foreign key (kupac_id) references kupac (kupac_id)
);

create table proizvod
(
	PROIZVOD_ID int auto_increment
		primary key,
	NAZIV varchar(64) not null,
	CENA float not null,
	TEZINA float not null,
	OPIS varchar(256) not null,
	ROK_TRAJANJA timestamp default current_timestamp() not null on update current_timestamp(),
	KOLICINA_NA_STANJU decimal not null
);

create table detaljiporudzbine
(
	DETALJI_PORUDZBINE_ID int auto_increment
		primary key,
	PORUDZBINA_ID int not null,
	PROIZVOD_ID int not null,
	KOLICINA decimal null,
	constraint detaljiporudzbine___fk_porudzbina
		foreign key (PORUDZBINA_ID) references porudzbina (PORUDZBINA_ID),
	constraint FKs2v9dtp5v08146sb7p7mukwpk
		foreign key (PORUDZBINA_ID) references porudzbina (PORUDZBINA_ID),
	constraint detaljiporudzbine___fk_proizvod
		foreign key (PROIZVOD_ID) references proizvod (PROIZVOD_ID),
	constraint FKtlj3q8twccdwtso3x6dt5db9o
		foreign key (PROIZVOD_ID) references proizvod (PROIZVOD_ID)
);

create table rola
(
	ROLA_ID int auto_increment
		primary key,
	NAZIV_ROLE varchar(16) not null
);

create table korisnik
(
	USER_ID int auto_increment
		primary key,
	ROLA_ID int null,
	EMAIL varchar(128) not null,
	LOZINKA varchar(128) not null,
	IME varchar(128) not null,
	ADRESA varchar(128) not null,
	GRAD varchar(64) not null,
	DRZAVA varchar(64) not null,
	TELEFON varchar(16) not null,
	constraint FKov6dnfb1o6xlwc8jkq7o6iunm
		foreign key (ROLA_ID) references rola (ROLA_ID)
);

