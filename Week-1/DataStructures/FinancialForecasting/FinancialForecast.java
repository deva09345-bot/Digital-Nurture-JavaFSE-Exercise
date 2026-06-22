package com.financial;
// Exercise 3: Financial Forecasting using Recursion
public class FinancialForecast {

    /**
     * Recursive: futureValue = presentValue * (1 + growthRate)^years
     * Time Complexity: O(n)
     */
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) return presentValue;
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    /**
     * Memoized version to avoid recomputation
     */
    private static java.util.Map<Integer, Double> memo = new java.util.HashMap<>();

    public static double calculateFutureValueMemo(double presentValue, double growthRate, int years) {
        if (years == 0) return presentValue;
        if (memo.containsKey(years)) return memo.get(years);
        double result = calculateFutureValueMemo(presentValue, growthRate, years - 1) * (1 + growthRate);
        memo.put(years, result);
        return result;
    }
}
