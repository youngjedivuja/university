void main() {
    Kurs kurs1 = new("se201", 4, 10);
    Kurs kurs2 = new("se211", 6, 12);
    Kurs kurs3 = new("se322", 8, 14);
    List<Kurs> arr = new() {kurs1, kurs2, kurs3};
    arr.Sort();
}

main();