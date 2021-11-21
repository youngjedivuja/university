import random


def fill_array(name, indeks):
    if len(name) % 2 == 0:
        arr = [random.randint(0, indeks) for _ in range(indeks)]
    else:
        arr = [random.uniform(-int(str(indeks)[:2]), int(str(indeks)[2:])) for _ in range(indeks)]

    arr.sort()

    return arr


if __name__ == '__main__':
    print(fill_array("Jovan", 3860))
