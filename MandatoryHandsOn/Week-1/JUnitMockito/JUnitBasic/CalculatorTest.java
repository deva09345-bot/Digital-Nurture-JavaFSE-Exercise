import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    @Order(1)
    void testAdd() {
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    @Order(2)
    void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    @Order(3)
    void testMultiply() {
        int result = calculator.multiply(4, 3);
        assertEquals(12, result);
    }

    @Test
    @Order(4)
    void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    @Order(5)
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }

    @Test
    @Order(6)
    void testAssertions() {
        assertTrue(5 > 3);
        assertFalse(3 > 5);
        assertNull(null);
        assertNotNull(calculator);
        assertEquals(8, calculator.add(5, 3));
    }
}
