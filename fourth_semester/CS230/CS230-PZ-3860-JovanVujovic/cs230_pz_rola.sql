create table rola
(
    ROLA_ID    int auto_increment
        primary key,
    NAZIV_ROLE varchar(16) not null
);

INSERT INTO `cs230-pz`.rola (ROLA_ID, NAZIV_ROLE) VALUES (1, 'Administrator');
INSERT INTO `cs230-pz`.rola (ROLA_ID, NAZIV_ROLE) VALUES (2, 'Zaposleni');