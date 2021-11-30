using System;

namespace CS322_DZ06_3860_JovanVujovic {
    internal class Program {
        public static void Main(string[] args) {
            Man athlete = new Athlete("Athlete1", 20);
            Man programmer = new Programmer("Programmer1", 20);
            
            athlete.run();
            programmer.run();
        }
    }

    interface IActivity {
        void run();
    }

    abstract class Man : IActivity {

        protected string Name;
        protected int Age;

        protected Man(string name, int age) {
            Name = name;
            Age = age;
        }

        public abstract void run();
    }

    class Athlete : Man {
        
        public Athlete(string name, int age): base(name, age) {
        }

        public override void run() {
            Console.WriteLine("Athlete is running 100m in 14 seconds");
        }
    }

    class Programmer : Man {
        
        public Programmer(string name, int age) : base(name, age) {
        }

        public override void run() {
            Console.WriteLine("Error 404 - Programmer who is able to run - not found");
        }
    }
}