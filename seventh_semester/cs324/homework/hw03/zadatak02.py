import random


def main():
    indeks = 3860
    num_list = []
    for _ in range(indeks):
        num_list.append(random.randint(1, 10000))

    num_list.sort(reverse=True)
    print(num_list)
    print(len(num_list))


if __name__ == '__main__':
    main()
