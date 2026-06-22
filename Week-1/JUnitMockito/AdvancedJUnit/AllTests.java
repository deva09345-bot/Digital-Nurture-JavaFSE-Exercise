import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Exercise 2: Test Suites
@Suite
@SelectClasses({EvenCheckerTest.class, ExceptionThrowerTest.class,
                PerformanceTesterTest.class, Calculator.class})
public class AllTests {}
