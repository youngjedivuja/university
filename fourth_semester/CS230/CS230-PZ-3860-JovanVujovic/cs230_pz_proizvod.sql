create table proizvod
(
    PROIZVOD_ID        int auto_increment
        primary key,
    NAZIV              varchar(64)                           not null,
    CENA               float                                 not null,
    TEZINA             float                                 not null,
    OPIS               varchar(256)                          not null,
    ROK_TRAJANJA       timestamp default current_timestamp() not null on update current_timestamp(),
    KOLICINA_NA_STANJU decimal                               not null
);

INSERT INTO `cs230-pz`.proizvod (PROIZVOD_ID, NAZIV, CENA, TEZINA, OPIS, ROK_TRAJANJA, KOLICINA_NA_STANJU) VALUES (1, 'testProizvod', 250, 500, 'Test', '2020-06-13 00:00:00', 500);
INSERT INTO `cs230-pz`.proizvod (PROIZVOD_ID, NAZIV, CENA, TEZINA, OPIS, ROK_TRAJANJA, KOLICINA_NA_STANJU) VALUES (8, 'Novi', 12, 150, 'Dobar', '2020-06-13 00:00:00', 200);