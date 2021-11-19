public class Main {
    public static void main(String[] args) {
        System.out.println("Num 16 is power of two: " + checkIfNumIsPowerOfTwo(16));
        System.out.println("Num 15 is power of two: " + checkIfNumIsPowerOfTwo(15));
    }

    private static boolean checkIfNumIsPowerOfTwo(int num){
        return (num & -num) == num;
    }
}
