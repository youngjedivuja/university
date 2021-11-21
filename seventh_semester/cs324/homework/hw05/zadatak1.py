from pprint import pprint


class Document:
    def __init__(self, name, word_count):
        self.name = name
        self.word_count = word_count


class Book(Document):
    code = 1

    def __init__(self, name, word_count, author, genre, year):
        super(Book, self).__init__(name, word_count)
        self.key = ("lib00" if self.code < 10 else "lib0") + str(self.code)
        self.author = author
        self.genre = genre
        self.year = year
        Book.code += 1

    def __repr__(self):
        return self.genre + " " + self.author + " " + self.name


def main():
    books = [
        Book("Knjiga 1", 100, "Autor 1", "Genre", 2008),
        Book("Knjiga 2", 100, "Autor 2", "Genre", 2008),
        Book("Knjiga 3", 100, "Autor 3", "Genre", 2008),
        Book("Knjiga 4", 100, "Autor 4", "Genre", 2008),
        Book("Knjiga 5", 100, "Autor 5", "Genre", 2008),
        Book("Knjiga 6", 100, "Autor 6", "Genre", 2008),
        Book("Knjiga 7", 100, "Autor 7", "Genre", 2008),
        Book("Knjiga 8", 100, "Autor 8", "Genre", 2008),
        Book("Knjiga 9", 100, "Autor 9", "Genre", 2008),
        Book("Knjiga 10", 100, "Autor 10", "Genre", 2008)
    ]

    books_dict = {book.key: book for book in books}
    pprint(books_dict)


if __name__ == '__main__':
    main()
