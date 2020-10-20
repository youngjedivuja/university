
import org.junit.Test;
import se321.v02.*;

import static org.junit.Assert.*;


public class MatematikaTest {

    @Test
    public void testSum(){
        System.out.println("sum");
        int a = 0;
        int b = 0;
        int expResult = 0;
        int result = Matematika.sum(a,b);
        assertEquals(expResult, result);
    }

    @Test
    public void evenTrue(){
        assertTrue(Matematika.even(10));
    }

    @Test
    public void evenFalse(){
        assertFalse(Matematika.even(11));
    }
}
