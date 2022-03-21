//s obzirom da koristim linux os, zadaci će biti urađeni bez windows formi pošto već nije akcent na njima

void z1() {
    int sum = 0;
    for (int i = 1; i <= 3000; i++) {
        sum += i;
    }
    Console.WriteLine("Resenje prvog zadatka je: " + sum);
}

void z2() {
    int sum = 0;
    int count = 100;
    int i = 1;
    while (count > i) {
        if (i % 2 == 0) {
            sum += i;
            count--;
        }
        i++;
    }
    Console.WriteLine("Resenje drugog zadatka je: " + sum);
}

void z3() {
    int sum = 0;
    for (int i = 21; i < 99; i++) {
        if (i % 2 == 0) {
            sum += i;
        }
    }
    Console.WriteLine("Resenje treceg zadatka je: " + sum);
}

void z4() {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int sum = arr.Sum();
    Console.WriteLine("Suma niza je: " + sum);
    int avg = sum / arr.Length;
    Console.WriteLine("Prosek niza je: " + avg);
}

void z5() {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Array.Sort(arr);
    int min = arr[0];
    int max = arr[arr.Length - 1];
    
    Console.WriteLine("max: " + max);
    Console.WriteLine("min: " + min);
}

void main() {
    z1();
    z2();
    z3();
    z4();
    z5();
}

main();