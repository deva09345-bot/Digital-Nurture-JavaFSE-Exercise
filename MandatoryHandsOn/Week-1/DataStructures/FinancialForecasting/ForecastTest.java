public class ForecastTest {
    public static void main(String[] args) {
        double presentValue = 10000.0;
        double growthRate   = 0.08;
        int    years        = 5;

        double futureRecursive = FinancialForecast.calculateFutureValue(presentValue, growthRate, years);
        System.out.printf("Future Value (recursive) after %d years at %.0f%% growth: $%.2f%n",
                years, growthRate * 100, futureRecursive);

        double futureMemo = FinancialForecast.calculateFutureValueMemo(presentValue, growthRate, years);
        System.out.printf("Future Value (memoized)  after %d years at %.0f%% growth: $%.2f%n",
                years, growthRate * 100, futureMemo);

        System.out.println("\n=== Forecast for 10 years ===");
        double future10 = FinancialForecast.calculateFutureValue(presentValue, growthRate, 10);
        System.out.printf("Future Value after 10 years: $%.2f%n", future10);
    }
}
