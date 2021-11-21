class Person:
    def __init__(self, name, surname):
        self.name = name
        self.surname = surname


class Student(Person):
    def __init__(self, name: str, surname: str, indeks: int, major: str, passed_exams: dict):
        super(Student, self).__init__(name, surname)
        self.indeks = indeks
        self.major = major
        self.__passed_exams = passed_exams

    def same_major(self, other: 'Student'):
        return self.major == other.major

    def passed_exams(self):
        return list(self.__passed_exams.keys())

    def same_passed_exam(self, other: 'Student'):
        return list(set(self.passed_exams()).intersection(set(other.passed_exams())))


def main():
    me = Student("Jovan", "Vujovic", 3860, "SE", {"SE325": 10, "CS101": 10})
    ilija = Student("Ilija", "Tijanic", 3328, "SE", {"CS101": 10})

    print(ilija.name + " and " + me.name + " have the same major: " + str(me.same_major(ilija)))

    print(me.name + " passed " + str(len(me.passed_exams())) + " exams")

    print(ilija.name + " passed " + str(len(ilija.passed_exams())) + " exams")

    print(ilija.name + " and " + me.name + " same exams passed: " + str(me.same_passed_exam(ilija)))


if __name__ == '__main__':
    main()
