package se321.v06;

public class Triangle {
    public static boolean checkTriangle(int a, int b, int c) {
        return (a + b > c && a + c > b && b + c > a) && (a > 0 && b > 0 && c > 0);
    }

    public static TriangleType checkTriangleType(int a, int b, int c) {
        if (checkTriangle(a, b, c)) {
            if (a == b && b == c) {
                return TriangleType.JEDNAKOSTRANICNI;
            } else if ((a == b) || (b == c) || (a == c)) {
                return TriangleType.JEDNAKOKRAKI;
            } else {
                return TriangleType.RAZNOSTRANI;
            }
        } else {
            return TriangleType.NEVALIDAN;
        }
    }
}
