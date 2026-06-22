import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 1: Parameterized Tests
public class EvenCheckerTest {

    EvenChecker checker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100})
    void testIsEven_withEvenNumbers(int number) {
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99})
    void testIsEven_withOddNumbers(int number) {
        assertFalse(checker.isEven(number));
    }

    @ParameterizedTest
    @CsvSource({"2, true", "3, false", "10, true", "11, false"})
    void testIsEven_csvSource(int number, boolean expected) {
        assertEquals(expected, checker.isEven(number));
    }
}
