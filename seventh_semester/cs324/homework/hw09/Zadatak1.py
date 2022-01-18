import numpy
import matplotlib.pyplot as plt


def calculate_num_of_grades(grades: []):
    new_grades = [grades.count(6), grades.count(7), grades.count(8), grades.count(9), grades.count(10)]
    return new_grades


def main():
    print("Unesite ocene od 6 do 10, 0 ocena je za kraj programa")
    grades = []

    while 1:
        grade = int(input("Ocena: "))
        if grade == 0:
            break
        if 5 < grade <= 10:
            grades.append(grade)
        print(numpy.average(grades))

    plt.plot(range(1, len(grades) + 1, 1), grades, label="Ocene")
    plt.legend()
    plt.grid()
    plt.show()

    grades_labels = ["6", "7", "8", "9", "10"]
    plt.pie(calculate_num_of_grades(grades), labels=grades_labels)
    plt.legend()
    plt.show()


if __name__ == '__main__':
    main()
