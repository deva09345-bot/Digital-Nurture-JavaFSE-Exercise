import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 1: Setting Up JUnit
// Exercise 2: Writing Basic JUnit Tests
// Exercise 3: Assertions in JUnit
// Exercise 4: AAA Pattern, Fixtures, Setup/Teardown
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach   // Setup - runs before each test
    void setUp() {
        calculator = new Calculator();
        System.out.println("Setup: Calculator initialized");
    }

    @AfterEach    // Teardown - runs after each test
    void tearDown() {
        calculator = null;
        System.out.println("Teardown: Calculator cleaned up");
    }

    // AAA Pattern
    @Test
    @Order(1)
    void testAdd() {
        // Arrange
        int a = 5, b = 3;
        // Act
        int result = calculator.add(a, b);
        // Assert
        assertEquals(8, result, "5 + 3 should equal 8");
    }

    @Test
    @Order(2)
    void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3));
    }

    @Test
    @Order(3)
    void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3));
    }

    @Test
    @Order(4)
    void testDivide() {
        assertEquals(2.5, calculator.divide(5, 2), 0.001);
    }

    // Exercise 3: Various Assertions
    @Test
    @Order(5)
    void testAssertions() {
        assertTrue(5 > 3);
        assertFalse(5 < 3);
        assertNull(null);
        assertNotNull(new Object());
        assertEquals(8, calculator.add(5, 3));
    }

    // Exception test
    @Test
    @Order(6)
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }
}
