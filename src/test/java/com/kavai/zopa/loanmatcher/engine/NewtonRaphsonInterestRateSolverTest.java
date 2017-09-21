package com.kavai.zopa.loanmatcher.engine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NewtonRaphsonInterestRateSolverTest {
    private double loanAmount;
    private double monthlyPayment;
    private int numberOfMonths;
    private double expectedValue;
    private NewtonRaphsonInterestRateSolver sut;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{20000, 450, 60, 12.5041},
                new Object[]{10000, 400, 36, 25.4534},
                new Object[]{10000, 333, 36, 12.1793},
                new Object[]{1000, 30.88, 36, 7.0064},
                new Object[]{1000, 30.78, 36, 6.7874},
                new Object[]{10000, 11000, 1, 120},
                new Object[]{1000, 1, 20000, 1.2}
        );
    }

    public NewtonRaphsonInterestRateSolverTest(double loanAmount, double monthlyPayment, int numberOfMonths,
                                               double expectedValue) {
        this.loanAmount = loanAmount;
        this.monthlyPayment = monthlyPayment;
        this.numberOfMonths = numberOfMonths;
        this.expectedValue = expectedValue;
    }

    @Before
    public void setUp() throws Exception {
        sut = new NewtonRaphsonInterestRateSolver();
    }

    @Test
    public void solve() throws Exception {
        final double actualValue = sut.solve(loanAmount, monthlyPayment, numberOfMonths) * 1200;
        assertEquals(expectedValue, actualValue, 0.0001);
    }

}