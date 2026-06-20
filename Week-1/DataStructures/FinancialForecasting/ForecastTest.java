package com.financial;

public class ForecastTest {
    public static void main(String[] args) {
        double presentValue = 10000.0;
        double growthRate   = 0.08;   // 8%
        int    years        = 5;

        double future = FinancialForecast.calculateFutureValue(presentValue, growthRate, years);
        System.out.printf("Future Value (recursive) after %d years: $%.2f%n", years, future);

        double futureMemo = FinancialForecast.calculateFutureValueMemo(presentValue, growthRate, years);
        System.out.printf("Future Value (memoized)  after %d years: $%.2f%n", years, futureMemo);
    }
}
