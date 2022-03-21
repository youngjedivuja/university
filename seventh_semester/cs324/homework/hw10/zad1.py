from collections import Counter

import pandas as pd
from matplotlib import pyplot as plt


def main():
    student_data = pd.read_csv("Student_izvestaj.csv")

    counted_grades = Counter(student_data["Ocena"])

    grades = []
    num_grades = []
    avg_grades = []
    fig = plt.figure()
    counter = 0
    sum_grades = 0

    for i in student_data["Ocena"]:
        sum_grades += i
        counter += 1
        avg_grades.append(sum_grades / counter)

    print(avg_grades)

    courses = student_data["Sifra"]
    for subj in range(len(courses)):
        courses[subj] = courses[subj][:2]

    grade_counter = Counter(courses)

    for grade, i in counted_grades.items():
        num_grades.append(i)
        grades.append(grade)

    fig_1 = fig.add_subplot(2, 2, 1)
    fig_1.pie(num_grades, labels=grades, startangle=0)

    fig.add_subplot(2, 2, 2)
    plt.barh(list(grade_counter.keys()), list(grade_counter.values()))
    plt.ylabel("Grupa Predmeta")
    plt.xlabel("Broj ocena")

    fig.add_subplot(2, 2, (3, 4))
    plt.plot(range(len(student_data["Ocena"])), avg_grades, label="Prosek")
    plt.plot(range(len(student_data["Ocena"])), student_data["Ocena"], label="Ocena")

    plt.savefig("Student_izvestaj.png")
    plt.show()


if __name__ == '__main__':
    main()
