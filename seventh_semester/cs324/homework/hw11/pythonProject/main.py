import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


def main():
    data = pd.read_csv("data.csv")

    print(np.average(data["Trajanje"]))

    print(data.groupby(["NivoStudija"])["Trajanje"].mean())

    print(data.groupby(["StudijskiProgram"])["Trajanje"].value_counts())

    print(data.groupby(["Univerzitet"])["Trajanje"].value_counts())

    duration = data["Trajanje"].to_list()

    plt.axvline(np.mean(duration), color="red")
    plt.hist(duration, edgecolor="black")
    plt.ylabel("Studenti")
    plt.xlabel("Trajanje")

    plt.show()

    plt.hist(data["Univerzitet"].to_list())
    plt.ylabel("Broj studenata")
    plt.xlabel("Univerziteti")
    plt.show()


if __name__ == '__main__':
    main()
