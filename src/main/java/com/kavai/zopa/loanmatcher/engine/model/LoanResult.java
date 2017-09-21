package com.kavai.zopa.loanmatcher.engine.model;

public class LoanResult {
    private final double rate;
    private final double monthlyRepayment;
    private final double totalRepayment;

    public LoanResult(double rate, double monthlyRepayment, double totalRepayment) {
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public double getRate() {
        return rate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }
}
