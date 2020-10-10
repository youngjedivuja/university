create table porudzbina
(
    PORUDZBINA_ID  int auto_increment
        primary key,
    IZNOS          float                                 not null,
    ADRESA_DOSTAVE varchar(128)                          not null,
    DATUM          timestamp default current_timestamp() not null on update current_timestamp(),
    STATUS         varchar(64)                           not null,
    kupac_id       int                                   not null,
    constraint porudzbina___fk_kupac
        foreign key (kupac_id) references kupac (kupac_id)
);

INSERT INTO `cs230-pz`.porudzbina (PORUDZBINA_ID, IZNOS, ADRESA_DOSTAVE, DATUM, STATUS, kupac_id) VALUES (5, 1222, 'AdresaTest', '2020-12-12 01:00:00', 'Na cekanju', 4);
INSERT INTO `cs230-pz`.porudzbina (PORUDZBINA_ID, IZNOS, ADRESA_DOSTAVE, DATUM, STATUS, kupac_id) VALUES (6, 123123, 'AdresaTest2', '2020-12-12 01:00:00', 'Na cekanju', 4);