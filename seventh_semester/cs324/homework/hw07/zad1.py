import re


def main():
    indeks = 3860
    target_num = str(indeks)[-1]

    with open("text.txt", "r") as data, open("filtered_addresses.txt", "w") as addresses:
        text = data.read()

        pattern = re.compile(r"^\d+\s.*", re.MULTILINE)

        for address in re.findall(pattern, text):
            print(address)
            if address[0] == target_num:
                addresses.write(address + "\n")


if __name__ == '__main__':
    main()
