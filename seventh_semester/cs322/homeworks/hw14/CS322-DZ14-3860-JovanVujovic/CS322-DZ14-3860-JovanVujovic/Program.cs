void Main() {
    string firstName;
    string lastName;
    DateOnly dateOfBirth;
    
    Console.WriteLine("Unesite ime: ");
    firstName = Console.ReadLine() ?? throw new ArgumentException("Nevalidan unos");
    Console.WriteLine("Unesite prezime: ");
    lastName = Console.ReadLine() ?? throw new ArgumentException("Nevalidan unos");
    string pattern = "dd/MM/yyyy";
    Console.WriteLine("Unesite datum rodjenja");
    dateOfBirth = DateOnly.ParseExact(Console.ReadLine() ?? throw new ArgumentException("Nevalidan unos"), pattern, null);
    string sign = calculateAstrologySing(dateOfBirth);
    Console.WriteLine("Horoskopski znak za : {" + firstName + " " + lastName + "{ rodjen/a {" + dateOfBirth + "} je: {" + sign + "}");
}

string calculateAstrologySing(DateOnly dateofBirth) {
    int month = dateofBirth.Month;
    int day = dateofBirth.Day;

    switch (month) {
        case 1:
            return day < 20 ? "Jarac" : "Vodolija";
        case 2:
            return day < 20 ? "Vodolija" : "Ribe";
        case 3:
            return day < 21 ? "Ribe" : "Ovan";
        case 4:
            return day < 20 ? "Ovan" : "Bik";
        case 5:
            return day < 22 ? "Bik" : "Blizanci";
        case 6 :
            return day < 22 ? "Blizanci" : "Rak";
        case 7:
            return day < 23 ? "Rak" : "Lav";
        case 8:
            return day < 24 ? "Lav" : "Devica";
        case 9:
            return day < 24 ? "Devica" : "Vaga";
        case 10:
            return day < 23 ? "Vaga" : "Strelac";
        case 11:
            return day < 22 ? "Strelac" : "Skorpija";
        case 12:
            return day < 22 ? "Strelac" : "Jarac";
        default:
            return "Nevalidan unos";
    }
}

Main();
