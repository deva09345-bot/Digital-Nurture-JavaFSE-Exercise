import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 5: Timeout and Performance Testing
public class PerformanceTesterTest {
    PerformanceTester tester = new PerformanceTester();

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testPerformTask() throws InterruptedException {
        tester.performTask();  // Must finish within 1 second
    }

    @Test
    @Timeout(500)  // 500ms in milliseconds
    void testComputeSum() {
        long result = tester.computeSum(1_000_000);
        assertEquals(500000500000L, result);
    }
}
