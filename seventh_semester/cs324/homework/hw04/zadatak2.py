def fib_iter(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    elif n > 1:
        fn = 0
        fn1 = 1
        fn2 = 2
        for _ in range(3, n):
            fn = fn1 + fn2
            fn1 = fn2
            fn2 = fn
        return fn
    else:
        return -1


def fib_rec(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    elif n > 1:
        return fib_rec(n - 1) + fib_rec(n - 2)
    else:
        return -1


if __name__ == '__main__':
    print(fib_iter(6))
    print(fib_rec(6))
