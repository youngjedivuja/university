package test;

import org.junit.Test;
import se321.v06.Triangle;
import se321.v06.TriangleType;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void checkTriangleTest1(){
        int a = 5;
        int b = 6;
        int c = 7;
        assertTrue(Triangle.checkTriangle(a,b,c));
    }
    @Test
    public void checkTriangleTest2(){
        int a = 9;
        int b = 6;
        int c = 9;
        assertTrue(Triangle.checkTriangle(a,b,c));
    }
    @Test
    public void checkTriangleTest3(){
        int a = 0;
        int b = 0;
        int c = 0;
        assertFalse(Triangle.checkTriangle(a,b,c));
    }

    @Test
    public void checkTriangleTest4(){
        int a = -3;
        int b = -4;
        int c = -7;
        assertFalse(Triangle.checkTriangle(a,b,c));
    }

    @Test
    public void checkTriangleType1(){
        assertEquals(TriangleType.JEDNAKOSTRANICNI, Triangle.checkTriangleType(3, 3 ,3));
    }
    @Test
    public void checkTriangleType2(){
        assertEquals(TriangleType.JEDNAKOKRAKI, Triangle.checkTriangleType(3, 3 ,5));
    }
    @Test
    public void checkTriangleType3(){
        assertEquals(TriangleType.RAZNOSTRANI, Triangle.checkTriangleType(3, 4 ,5));
    }
    @Test
    public void checkTriangleType4(){
        assertEquals(TriangleType.NEVALIDAN, Triangle.checkTriangleType(0, 3 ,3));
    }
    @Test
    public void checkTriangleType5(){
        assertEquals(TriangleType.NEVALIDAN, Triangle.checkTriangleType(-1, 3 ,3));
    }
    @Test
    public void checkTriangleType6(){
        assertEquals(TriangleType.NEVALIDAN, Triangle.checkTriangleType(7, 3 ,3));
    }
}
