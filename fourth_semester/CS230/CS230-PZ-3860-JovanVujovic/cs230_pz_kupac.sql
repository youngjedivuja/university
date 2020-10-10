create table kupac
(
    kupac_id        int auto_increment
        primary key,
    naziv           varchar(64) not null,
    kontakt_telefon varchar(64) null
);

INSERT INTO `cs230-pz`.kupac (kupac_id, naziv, kontakt_telefon) VALUES (4, 'TestKupac', 'Testtelefon');