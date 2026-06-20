import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 4: Exception Testing
public class ExceptionThrowerTest {
    ExceptionThrower thrower = new ExceptionThrower();

    @Test
    void testThrowException() {
        assertThrows(IllegalArgumentException.class, thrower::throwException);
    }

    @Test
    void testDivideByZero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
            () -> thrower.divide(10, 0));
        assertEquals("Division by zero", ex.getMessage());
    }
}
