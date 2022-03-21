import numpy as np
import matplotlib.pyplot as plt


def g(x):
    return np.exp((-1 / 5) * x) * np.cos(5 * x)


def h(x):
    return np.exp((-1 / 2) * x) * np.sin(2 * x)


def main():
    x = np.linspace(0, 5, 500)
    x_func = g(x)
    y_func = h(x)
    line, = plt.plot(x, x_func, '--', label="$g_x$")
    line.set_color('black')
    plt.plot(x, y_func, 'r-', label="$h_x$")
    plt.xlabel("x")
    plt.xlim(0, 5)
    plt.ylim(-1, 1)
    plt.ylabel("funkcije")
    plt.legend()
    plt.grid()
    plt.savefig("funkcije.png")
    plt.show()


if __name__ == '__main__':
    main()
