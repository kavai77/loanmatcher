package com.kavai.zopa.loanmatcher.engine;

import com.google.inject.Inject;
import com.kavai.zopa.loanmatcher.engine.configuration.LoanNumberOfMonths;
import com.kavai.zopa.loanmatcher.engine.model.Lender;

import java.util.List;

import static java.lang.Math.min;
import static java.lang.Math.pow;

class MonthlyPaymentCalculator {
    private final int loanNumberOfMonths;

    @Inject
    public MonthlyPaymentCalculator(@LoanNumberOfMonths int loanNumberOfMonths) {
        this.loanNumberOfMonths = loanNumberOfMonths;
    }

    public double calculateMonthlyPayment(List<Lender> selectedLenders, double requestedAmount) {
        double monthlyPayment = 0;
        double totalLoanAmount = 0;
        for (Lender lender: selectedLenders) {
            double currentLoanAmount = min(lender.getAvailable(), requestedAmount - totalLoanAmount);
            totalLoanAmount += currentLoanAmount;
            monthlyPayment += calculateMonthlyPayment(currentLoanAmount, lender.getRate());
        }
        return monthlyPayment;
    }

    private double calculateMonthlyPayment(double loanAmount, double interestRate) {
        final double interestRateByMonth = interestRate / 12;
        return loanAmount * interestRateByMonth * pow(1 + interestRateByMonth, loanNumberOfMonths) /
                (pow(1 + interestRateByMonth, loanNumberOfMonths) - 1);
    }
}
