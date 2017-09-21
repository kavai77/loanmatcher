package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.model.Lender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MonthlyPaymentCalculatorTest {
    private final List<Lender> selectedLenders;
    private final int loanNumberOfMonths;
    private final double requestedAmount;
    private final double expectedMonthlyPayment;

    private MonthlyPaymentCalculator sut;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(
                new Object[]{
                    asList(new Lender("l1", 0.07, 1000)),
                    36,
                    1000,
                    30.88
                },
                new Object[]{
                    asList(new Lender("l1", 0.1, 30000)),
                    36,
                    20000,
                    645.34
                },
                new Object[]{
                        asList(
                            new Lender("l1", 0.07, 1000),
                            new Lender("l2", 0.08, 3000)
                        ),
                        36,
                        3000,
                        93.55
                }
        );
    }

    public MonthlyPaymentCalculatorTest(List<Lender> selectedLenders, int loanNumberOfMonths, double requestedAmount,
                                        double expectedMonthlyPayment) {
        this.selectedLenders = selectedLenders;
        this.loanNumberOfMonths = loanNumberOfMonths;
        this.requestedAmount = requestedAmount;
        this.expectedMonthlyPayment = expectedMonthlyPayment;
    }

    @Before
    public void setUp() throws Exception {
        sut = new MonthlyPaymentCalculator(loanNumberOfMonths);
    }

    @Test
    public void calculateMonthlyPayment() throws Exception {
        assertEquals(expectedMonthlyPayment, sut.calculateMonthlyPayment(selectedLenders, requestedAmount), 0.01);
    }

}