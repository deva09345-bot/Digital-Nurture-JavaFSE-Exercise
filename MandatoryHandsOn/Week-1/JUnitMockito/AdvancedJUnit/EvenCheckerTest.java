import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {

    EvenChecker checker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100})
    void testIsEven_EvenNumbers(int number) {
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99})
    void testIsEven_OddNumbers(int number) {
        assertFalse(checker.isEven(number));
    }

    @ParameterizedTest
    @CsvSource({"2, true", "3, false", "10, true", "11, false"})
    void testIsEven_CsvSource(int number, boolean expected) {
        assertEquals(expected, checker.isEven(number));
    }
}
