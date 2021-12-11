def average(num_array):
    tmp = 0
    for num in num_array:
        if isinstance(num, int) or isinstance(num, float):
            tmp += num
        elif isinstance(num, str):
            raise TypeError("Num cannot be string type")
        elif isinstance(num, bool):
            raise TypeError("Num cannot be boolean type")

    return tmp / len(num_array)


if __name__ == '__main__':
    print(average([1, 5.4, 7.4, 10.5, 12.7]))
    try:
        print(average([1, 'a', 7.4, 10.5, 12.7]))
    except TypeError as e:
        print(e)
