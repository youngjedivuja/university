import abc
from datetime import datetime


class Osoba(abc.ABC):
    pass


class Student(Osoba):
    # u zadatku stoji da treba dodati posebnu metodu koja popunjava atribute klase
    # pretpostavljam da se misli na konstruktor jer on popunjava na osnovu prosledjenih parametara
    def __init__(self, ime, prezime, broj_indeksa, godina_upisa):
        self.ime = ime
        self.prezime = prezime
        self.broj_indeksa = broj_indeksa
        self.godina_upisa = godina_upisa
        self.polozeni_ispiti = []

    def dodaj_polozeni_ispit(self):
        print("Dodavanje položenog ispita za: " + self.ime)

        sifra = input("Šifra predmeta:")
        naziv = input("Naziv predmeta:")
        ocena_str = input("Ocena: ")
        date_str = input("Datum polaganja: ")

        try:
            ocena = int(ocena_str)
        except ValueError:
            raise ValueError("Pogrešan unos")
        if not (6 <= ocena <= 10):
            raise ValueError("Pogrešan unos")

        polozen_ispit = {}
        polozen_ispit["ocena"] = ocena

        try:
            date = datetime.strptime(date_str, "%m %Y").date()
        except ValueError:
            raise ValueError("Pogrešan unos")
        if date.year > datetime.now().year:
            raise ValueError("Pogrešan unos")
        if date.year == datetime.now().year and date.month > datetime.now().month:
            raise ValueError("Pogrešan unos")
        if date.year < int(self.godina_upisa):
            raise ValueError("Pogrešan unos")

        polozen_ispit["date"] = date
        polozen_ispit["sifra"] = sifra
        polozen_ispit["naziv"] = naziv

        self.polozeni_ispiti.append(polozen_ispit)

    def prvi_ispit(self):
        self.polozeni_ispiti.sort(key=lambda x: x['date'])
        prvi_ispit = self.polozeni_ispiti[0]

        print("Prvi ispit")
        print("Student:     " + self.ime)
        print("Ispit:       " + prvi_ispit['sifra'])
        print("Ocena:       " + str(prvi_ispit['ocena']))
        print("Datum:       " + str(prvi_ispit['date'].month) + "/" + str(prvi_ispit['date'].year))

    def najbolji_ispit(self):
        self.polozeni_ispiti.sort(key=lambda x: x['ocena'], reverse=True)
        najbolji_ispit = self.polozeni_ispiti[0]

        print("Najbolji ispit:")
        print("Student:     " + self.ime)
        print("Ispit:       " + najbolji_ispit['sifra'])
        print("Ocena:       " + str(najbolji_ispit['ocena']))
        print("Datum:       " + str(najbolji_ispit['date'].month) + "/" + str(najbolji_ispit['date'].year))


def main():
    student1 = Student("Jovan", "Vujovic", "3860", "2017")
    student1.dodaj_polozeni_ispit()
    student1.dodaj_polozeni_ispit()
    student1.prvi_ispit()
    student1.najbolji_ispit()


if __name__ == '__main__':
    main()
