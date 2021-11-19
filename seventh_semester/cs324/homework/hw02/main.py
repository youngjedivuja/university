def is_prime(num):
    if num > 1:
        for n in range(2, num):
            if (num % n) == 0:
                return False
        return True
    else:
        return False


def print_prime_nums(limit):
    for n in range(limit):
        if is_prime(n):
            print(n)


def main():
    print_prime_nums(10)


if __name__ == '__main__':
    main()
