package com.kavai.zopa.loanmatcher.engine;

interface InterestRateSolver {
    double solve(double loanAmount, double monthlyPayment, int numberOfMonths);
}
