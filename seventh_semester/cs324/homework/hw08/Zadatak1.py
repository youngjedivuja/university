import numpy


def main():
    matrix1 = numpy.identity(3)
    numpy.fill_diagonal(matrix1, 1)
    print(matrix1)

    matrix2 = numpy.zeros((4, 4))
    matrix_temp = numpy.identity(2)
    matrix_temp.fill(10)
    matrix2[1:-1, 1:-1] = matrix_temp
    print(matrix2)

    matrix3 = numpy.ones((5, 5))
    matrix_temp = numpy.zeros((3, 3))
    matrix_temp[1, 1] = 1
    matrix3[1:-1, 1:-1] = matrix_temp
    print(matrix3)


if __name__ == '__main__':
    main()
