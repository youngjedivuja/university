def main():
    suma = 0
    while True:
        str_broj = input("Unesite broj: ")
        if str_broj.strip(" \n") == "":
            print("Suma je: " + str(suma))
            break
        try:
            broj = int(str_broj)
            suma += broj
            print("Suma je: " + str(suma))
        except ValueError:
            print("Pogrešan unos")


if __name__ == '__main__':
    main()
