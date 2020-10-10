create table korisnik
(
    USER_ID int auto_increment
        primary key,
    ROLA_ID int          null,
    EMAIL   varchar(128) not null,
    LOZINKA varchar(128) not null,
    IME     varchar(128) not null,
    ADRESA  varchar(128) not null,
    GRAD    varchar(64)  not null,
    DRZAVA  varchar(64)  not null,
    TELEFON varchar(16)  not null,
    constraint FKov6dnfb1o6xlwc8jkq7o6iunm
        foreign key (ROLA_ID) references rola (ROLA_ID)
);

INSERT INTO `cs230-pz`.korisnik (USER_ID, ROLA_ID, EMAIL, LOZINKA, IME, ADRESA, GRAD, DRZAVA, TELEFON) VALUES (1, 1, 'jovan', 'test', 'Jovan', 'test', 'test', 'test', 'test');
INSERT INTO `cs230-pz`.korisnik (USER_ID, ROLA_ID, EMAIL, LOZINKA, IME, ADRESA, GRAD, DRZAVA, TELEFON) VALUES (4, 2, 'primer', 'primer', 'Jovan', 'test', 'test', 'test', 'test');
INSERT INTO `cs230-pz`.korisnik (USER_ID, ROLA_ID, EMAIL, LOZINKA, IME, ADRESA, GRAD, DRZAVA, TELEFON) VALUES (5, 1, 'email1', 'email1', 'Jovan', 'email1', 'email1', 'email1', 'email1');
INSERT INTO `cs230-pz`.korisnik (USER_ID, ROLA_ID, EMAIL, LOZINKA, IME, ADRESA, GRAD, DRZAVA, TELEFON) VALUES (7, 2, 'testEmail', 'testEmail', 'JovanZaposleni', 'Adresatest', 'Nis', 'Srbija', '067456798');