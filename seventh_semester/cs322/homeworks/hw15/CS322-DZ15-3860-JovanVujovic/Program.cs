int time;
float rate, cash, interest;

void Main() {
    //cash input
    Console.WriteLine("Enter cash amount: ");
    cash = Convert.ToSingle(Console.ReadLine());
    if (cash <= 0)
        throw new ArgumentException("Cash must be positive");
    
    //time input
    Console.WriteLine("Enter amount of days for calculating your interest: ");
    time = Convert.ToInt32(Console.ReadLine());
    if (time <= 0)
        throw new ArgumentException("The amount of days for calculating the interest must be positive");
    
    //Interest rate input
    Console.WriteLine("Enter the interest rate to calculate: ");
    rate = Convert.ToSingle(Console.ReadLine());
    if (rate < 0)
        throw new ArgumentException("Rate cannot be negative");

    interest = cash * time * rate / 100;
    Console.WriteLine("Your calculated interest is: " + interest);
}

Main();