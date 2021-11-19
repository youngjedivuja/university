from math import log


def check_if_num_is_power_of_two(num):
    res = log(num, 2).is_integer()

    print("Num " + str(num) + " is power of 2: " + str(res))


if __name__ == '__main__':
    check_if_num_is_power_of_two(16)
    check_if_num_is_power_of_two(15)

