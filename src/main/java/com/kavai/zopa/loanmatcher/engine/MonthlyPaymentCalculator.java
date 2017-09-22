package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.model.Lender;

import java.util.List;

public interface MonthlyPaymentCalculator {
    double calculateMonthlyPayment(List<Lender> selectedLenders, double requestedAmount);
}
