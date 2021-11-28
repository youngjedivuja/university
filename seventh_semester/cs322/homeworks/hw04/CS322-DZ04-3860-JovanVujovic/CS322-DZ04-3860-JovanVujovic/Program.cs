using System;
using System.Text;

namespace CS322_DZ04_3860_JovanVujovic {
    internal static class Program {
        public static void Main(string[] args) {
            Console.WriteLine(Zad1(99));
            Console.WriteLine(Zad2());
            Zad03();
        }

        public static int Zad1(int n) {
            int sum = 0;

            for (int i = 1; i < n; i++) {
                if (i % 2 != 0) {
                    sum += i;
                }
            }

            return sum;
        }

        public static string Reverse(int n) {
            string s = n.ToString();
            StringBuilder sb = new StringBuilder();
            for (int i = s.Length - 1; i >= 0; i--) {
                sb.Append(s[i]);
            }

            return sb.ToString();
        }

        public static string Zad2() {
            StringBuilder sb = new StringBuilder();
            for (int i = 8; i <= 16; i++) {
                sb.Append(i + ": ");
                sb.Append(Reverse(i * 2));
                sb.Append("; ");
            }

            return sb.ToString();
        }

        public static void Zad03() {
            Console.WriteLine("Unesite broj");
            string input = Console.In.ReadLine();
            if (int.Parse(input ?? throw new InvalidOperationException("Nije unet broj")) >= 1000000000) {
                throw new InvalidOperationException("Invalid");
            }

            for (int i = 0; i < input.Length - 1; i++) {
                if (input[i] >= input[i + 1]) {
                    Console.WriteLine("Nema strogo rastuce cifre");
                    return;
                }
            }
            Console.WriteLine("Ima strogo rastuce cifre");
        }
    }
}