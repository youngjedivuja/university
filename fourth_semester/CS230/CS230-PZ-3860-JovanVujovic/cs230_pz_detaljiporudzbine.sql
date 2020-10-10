create table detaljiporudzbine
(
    DETALJI_PORUDZBINE_ID int auto_increment
        primary key,
    PORUDZBINA_ID         int     not null,
    PROIZVOD_ID           int     not null,
    KOLICINA              decimal null,
    constraint detaljiporudzbine___fk_porudzbina
        foreign key (PORUDZBINA_ID) references porudzbina (PORUDZBINA_ID),
    constraint FKs2v9dtp5v08146sb7p7mukwpk
        foreign key (PORUDZBINA_ID) references porudzbina (PORUDZBINA_ID),
    constraint detaljiporudzbine___fk_proizvod
        foreign key (PROIZVOD_ID) references proizvod (PROIZVOD_ID),
    constraint FKtlj3q8twccdwtso3x6dt5db9o
        foreign key (PROIZVOD_ID) references proizvod (PROIZVOD_ID)
);

INSERT INTO `cs230-pz`.detaljiporudzbine (DETALJI_PORUDZBINE_ID, PORUDZBINA_ID, PROIZVOD_ID, KOLICINA) VALUES (4, 5, 1, 123);
INSERT INTO `cs230-pz`.detaljiporudzbine (DETALJI_PORUDZBINE_ID, PORUDZBINA_ID, PROIZVOD_ID, KOLICINA) VALUES (5, 5, 1, 300);