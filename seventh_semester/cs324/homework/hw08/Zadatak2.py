import sqlite3
import pandas
from datetime import datetime


def create_book(conn):
    kat_broj = input("Kat broj: ")
    naslov = input("Naslov: ")
    izdavac = input("Izdavac: ")
    godina_izdavanja = input("Godina izdavanja: ")

    if int(godina_izdavanja) > datetime.now().year:
        raise ValueError("Godinu izdavanja ne možete setovati u budućnosti")

    book = (kat_broj, naslov, izdavac, godina_izdavanja)

    conn.execute("insert into "
                 "knjige (kat_broj, naslov, izdavac, godina_izdavanja, izdata) "
                 "values (?, ?, ?, ?, 0)",
                 book)
    conn.commit()


def podesi_izdat(conn):
    kat_broj = input("Kat broj: ")

    conn.execute("update knjige set izdata = 1 where kat_broj = ?", (kat_broj,))
    conn.commit()


def generate_csv(conn):
    gen = pandas.read_sql("select naslov, izdavac, godina_izdavanja from knjige", conn)
    print(gen)
    gen.to_csv("izdate_knjige.csv", columns=("naslov", "izdavac", "godina_izdavanja"))
    print(gen)


def main():
    conn = sqlite3.connect("biblioteka.db")
    conn.execute(
        "create table if not exists "
        "knjige (kat_broj int, naslov text, izdavac text, godina_izdavanja int, izdata bool)")

    while 1:
        try:
            command = input("Unesite komandu (kreiraj, izdaj, generiši, izadji): ")
            if command == "kreiraj":
                create_book(conn)
            elif command == "izdaj":
                podesi_izdat(conn)
            elif command == "generiši":
                generate_csv(conn)
                break
            elif command == "izadji":
                break
        except KeyboardInterrupt:
            break


if __name__ == '__main__':
    main()
