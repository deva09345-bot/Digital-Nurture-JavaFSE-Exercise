import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    void firstTest() {
        System.out.println("Executing test 1");
        assertTrue(true);
    }

    @Test
    @Order(2)
    void secondTest() {
        System.out.println("Executing test 2");
        assertEquals(4, 2 + 2);
    }

    @Test
    @Order(3)
    void thirdTest() {
        System.out.println("Executing test 3");
        assertNotNull("value");
    }

    @Test
    @Order(4)
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testTimeout() throws InterruptedException {
        Thread.sleep(100);
        assertTrue(true);
    }

    @Test
    @Order(5)
    void testException() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
    }
}
