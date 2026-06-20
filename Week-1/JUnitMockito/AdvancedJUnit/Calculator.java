import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 3: Test Execution Order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Calculator {

    @Test @Order(1)
    void firstTest()  { System.out.println("Test 1 runs first");  assertTrue(true); }

    @Test @Order(2)
    void secondTest() { System.out.println("Test 2 runs second"); assertTrue(true); }

    @Test @Order(3)
    void thirdTest()  { System.out.println("Test 3 runs third");  assertTrue(true); }
}
