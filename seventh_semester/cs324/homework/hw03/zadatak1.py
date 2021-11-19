def main():
    exams = {}
    num_of_exams = int(input("Koliko imate polozenih ispita?: "))

    for i in range(num_of_exams):
        exam = input("Unesite šifru predmeta: ")
        grade = input("Unesite ocenu: ")
        exams[exam] = grade

    query = input("Za koji ispit zelite informacije?: ")

    grade = exams[query]

    print("Ocena za izabrani predmet " + query + " je: " + str(grade))


if __name__ == '__main__':
    main()
