package com.kavai.zopa.loanmatcher.engine;

import static java.lang.Math.pow;

class NewtonRaphsonInterestRateSolver implements InterestRateSolver {
    public double solve(double loanAmount, double monthlyPayment, int numberOfMonths) {
        final InputParameters inputParameters = new InputParameters(loanAmount, monthlyPayment, numberOfMonths);
        double currentValue = initalGuess(inputParameters);
        double rateFunctionValue = rateFunction(currentValue, inputParameters);
        while (!isGoodEnough(rateFunctionValue)) {
            currentValue = currentValue - rateFunctionValue / rateDerivativeFunction(currentValue, inputParameters);
            rateFunctionValue = rateFunction(currentValue, inputParameters);
        }
        return currentValue;
    }

    private double initalGuess(InputParameters p) {
        return 2 * (p.numberOfMonths * p.monthlyPayment - p.loanAmount) / (p.numberOfMonths * p.loanAmount);
    }

    private double rateFunction(double rate, InputParameters p) {
        final double rateToTheMonth = pow(1 + rate, p.numberOfMonths);
        return rate * rateToTheMonth / (rateToTheMonth - 1) - p.monthlyPayment / p.loanAmount;
    }

    private double rateDerivativeFunction(double rate, InputParameters p) {
        return pow(1 + rate, p.numberOfMonths - 1) * (pow(1 + rate, p.numberOfMonths + 1) + (-p.numberOfMonths - 1) * rate - 1) /
                pow(pow(1 + rate, p.numberOfMonths) - 1, 2);
    }

    private boolean isGoodEnough(double x) {
        return Math.abs(x) < 0.0000001;
    }

    private static class InputParameters {
        private final double loanAmount;
        private final double monthlyPayment;
        private final int numberOfMonths;

        InputParameters(double loanAmount, double monthlyPayment, int numberOfMonths) {
            this.loanAmount = loanAmount;
            this.monthlyPayment = monthlyPayment;
            this.numberOfMonths = numberOfMonths;
        }
    }
}