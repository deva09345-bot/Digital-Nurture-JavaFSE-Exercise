// Exercise 5: Timeout and Performance Testing
public class PerformanceTester {
    public void performTask() throws InterruptedException {
        Thread.sleep(100); // Simulates some work
    }

    public long computeSum(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) sum += i;
        return sum;
    }
}
