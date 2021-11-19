import random


def main():
    indeks = 3860
    m = indeks // 3

    begin = int(str(indeks)[0:2])
    end = int(str(indeks)[2:])

    data = dict((i, random.uniform(begin, end)) for i, _ in enumerate(range(m)))

    print(data[indeks % 3])


if __name__ == '__main__':
    main()
