import sqlite3


class Course:
    def __init__(self, code, full_name, professor, year_of_study):
        super().__init__()
        self.code = code
        self.professor = professor
        self.full_name = full_name
        self.year_of_study = year_of_study

    def __repr__(self) -> str:
        return self.code + " " + self.professor

def main(cursor):
    create_table_sql = """
    	create table if not exists course (
    		code varchar(8) primary key,
    		full_name varchar(64) not null,
    		professor varchar(64) not null,
    		year_of_study int not null
    	);
    	"""
    cursor.execute(create_table_sql)

    cs322 = Course("CS322", "Programiranje u C#", "Milos Lomovic", "4")
    cs324 = Course("CS324", "Skripting jezici", "Nemanja Zdravkovic", "4")
    cs225 = Course("CS225", "Operativni sistemi", "Nemanja Zdravkovic", "4")
    it381 = Course("IT381", "Bezbednost i Zastita informacija", "Milena Bogdanovic", "4")

    insert(cs322, cursor)
    insert(cs324, cursor)
    insert(cs225, cursor)
    insert(it381, cursor)

    courses = find_by_professor("Nemanja Zdravkovic", cursor)
    for course in courses:
        print(course)


def insert(course, cursor):
    cursor.execute("insert into course values (?, ?, ?, ?)",
                   (course.code, course.full_name, course.professor, course.year_of_study))

def find_by_professor(prof, cursor):
    cursor.execute("select * from course where professor = ?;", (prof,))
    return list(map(lambda r: Course(*r), cursor.fetchall()))


if __name__ == '__main__':
    conn = sqlite3.connect(":memory:")
    cursor = conn.cursor()
    main(cursor)
