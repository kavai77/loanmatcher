package com.kavai.zopa.loanmatcher.engine;

import com.google.inject.Inject;
import com.kavai.zopa.loanmatcher.engine.configuration.LoanNumberOfMonths;
import com.kavai.zopa.loanmatcher.engine.exception.InputValidationException;
import com.kavai.zopa.loanmatcher.engine.exception.MarketLiquidityException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;
import com.kavai.zopa.loanmatcher.engine.model.LoanInput;
import com.kavai.zopa.loanmatcher.engine.model.LoanResult;

import java.io.IOException;
import java.util.List;

public class LenderMatcherSolver {
    private final InputValidator inputValidator;
    private final MarketCSVParser marketCSVParser;
    private final LenderSelector lenderSelector;
    private final MonthlyPaymentCalculator monthlyPaymentCalculator;
    private final InterestRateSolver interestRateSolver;
    private final int loanNumberOfMonths;

    @Inject
    public LenderMatcherSolver(InputValidator inputValidator, MarketCSVParser marketCSVParser, LenderSelector lenderSelector,
                               MonthlyPaymentCalculator monthlyPaymentCalculator,
                               InterestRateSolver interestRateSolver, @LoanNumberOfMonths int loanNumberOfMonths) {
        this.inputValidator = inputValidator;
        this.marketCSVParser = marketCSVParser;
        this.lenderSelector = lenderSelector;
        this.monthlyPaymentCalculator = monthlyPaymentCalculator;
        this.interestRateSolver = interestRateSolver;
        this.loanNumberOfMonths = loanNumberOfMonths;
    }

    public LoanResult solve(LoanInput loanInput) throws IOException, MarketLiquidityException, InputValidationException {
        inputValidator.validateInput(loanInput);
        final List<Lender> lenders = marketCSVParser.parseCsvMarketModelFile(loanInput.getMarketFilePath());
        final List<Lender> selectedLenders = lenderSelector.selectBestRateLenders(lenders, loanInput.getRequestedAmount());
        final double monthlyPayment = monthlyPaymentCalculator.calculateMonthlyPayment(selectedLenders, loanInput.getRequestedAmount());
        final double monthlyInterestRate = interestRateSolver.solve(loanInput.getRequestedAmount(), monthlyPayment, loanNumberOfMonths);
        return new LoanResult(monthlyInterestRate * 12, monthlyPayment, monthlyPayment * loanNumberOfMonths);
    }
}
