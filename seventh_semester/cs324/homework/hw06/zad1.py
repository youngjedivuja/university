import csv

courseList = {
    'CS322': {'lectures': '14:50', 'practice': '17:20'},
    'CS225': {'lectures': '15:40', 'practice': '18:10'},
    'CS324': {'lectures': '09:50', 'practice': '12:20'},
    'IT381': {'lectures': '16:30', 'practice': '19:00'}
}


def main():
    with open("course_schedule.csv", "w") as file:
        file = csv.writer(file)
        for course, sched in courseList.items():
            file.writerow([course,
                           "Lectures: " + sched['lectures'],
                           "Practice: " + sched['practice']])


if __name__ == '__main__':
    main()
