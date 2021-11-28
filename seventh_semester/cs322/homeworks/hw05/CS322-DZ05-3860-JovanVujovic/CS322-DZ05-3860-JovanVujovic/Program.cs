using System;
using System.Text;

namespace CS322_DZ05_3860_JovanVujovic {
    internal class Program {
        public static void Main(string[] args) {
            Zad01();
            Zad02();
        }

        private static void Zad01() {
            Console.WriteLine("Unesite broj: ");
            string num = Console.In.ReadLine();
            Console.WriteLine("Unesite vrednost stepena");
            string power = Console.In.ReadLine();

            double numValue = double.Parse(num);
            double powerValue = double.Parse(power);

            Console.WriteLine("Rezultat: " + Power(numValue, powerValue));
        }

        private static double Power(double num, double power) {
            if (num == 0)
                throw new ArgumentException("Broj ne sme biti jednak nuli");


            if (power < 0)
                throw new ArgumentException("Stepen mora biti veci ili jednak nuli");

            return Math.Pow(num, power);
        }
        
        public static void Zad02() {
            Visitor v1 = new Visitor();
            v1.FirstName = Helper.GetRandomString();
            v1.LastName = Helper.GetRandomString();
            v1.SerialNum = Helper.GetRandomInteger();
            
            Visitor v2 = new Visitor();
            v2.FirstName = Helper.GetRandomString();
            v2.LastName = Helper.GetRandomString();
            v2.SerialNum = Helper.GetRandomInteger();
            
            Console.WriteLine(v1);
            Console.WriteLine(v2);
            
        }
    }

    internal class Visitor {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int SerialNum { get; set; }
    }

    internal class Helper {
        public static string GetRandomString() {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                if (i == 0) {
                    sb.Append(random.Next('A', 'Z'));
                    continue;
                }

                sb.Append(random.Next('a', 'z'));
            }

            return sb.ToString();
        }

        public static int GetRandomInteger() {
            Random random = new Random();
            return random.Next(1, 200);
        }
    }

    
}