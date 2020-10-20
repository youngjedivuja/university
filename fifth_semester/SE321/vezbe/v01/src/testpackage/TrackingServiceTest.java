import exceptions.InvalidGoalException;
import org.junit.*;
import proteintracker.TrackingService;

import static org.junit.Assert.assertEquals;

public class TrackingServiceTest {
    private TrackingService service;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("@BeforeClass gets called only once before class");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("@AfterClass gets called only once after class");
    }

    @Before
    public void setUp() {
        service = new TrackingService();
        System.out.println("@Before gets called before every method");
    }

    @After
    public void tearDown() {
        System.out.println("@After gets called after every method");
    }

    @Test
    public void newTrackingServiceTotalIsZero() {
        assertEquals("Tracking service was not zero!", 0, service.getTotal());
    }

    @Test
    public void whenAddingProteinTotalIncreasesByThatAmount() {
        service.addProtein(5);
        assertEquals(5, service.getTotal());
    }

    @Test
    public void whenRemovingProteinTotalRemainsZero() {
        service.removeProtein(5);
        assertEquals(0, service.getTotal());
    }

    @Test(expected = InvalidGoalException.class)
    public void whenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException {
        service.setGoal(-3);
    }

    @Test(timeout = 200)
    public void badTest(){
        for(int i = 0; i < 10000000; i++){
            service.addProtein(1);
        }
    }
}
