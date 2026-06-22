// Exercise 4: Exception Testing
public class ExceptionThrower {
    public void throwException() {
        throw new IllegalArgumentException("Invalid argument provided");
    }

    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }
}
