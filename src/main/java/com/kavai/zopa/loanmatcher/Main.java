package com.kavai.zopa.loanmatcher;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kavai.zopa.loanmatcher.engine.EngineModule;
import com.kavai.zopa.loanmatcher.engine.LenderMatcherSolver;
import com.kavai.zopa.loanmatcher.engine.exception.InputValidationException;
import com.kavai.zopa.loanmatcher.engine.exception.MarketLiquidityException;
import com.kavai.zopa.loanmatcher.engine.model.LoanInput;
import com.kavai.zopa.loanmatcher.engine.model.LoanResult;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Two parameters expected: csv file, requested loan");
            return;
        }
        final String marketFilePath = args[0];
        final int requestedAmount = Integer.parseInt(args[1]);
        Injector injector = Guice.createInjector(new EngineModule());
        final LenderMatcherSolver lenderMatcherSolver = injector.getInstance(LenderMatcherSolver.class);
        try {
            final LoanResult loanResult = lenderMatcherSolver.solve(new LoanInput(marketFilePath, requestedAmount));
            System.out.printf("Requested amount: £%d%n", requestedAmount);
            System.out.printf("Rate: %.1f%%%n", loanResult.getRate() * 100);
            System.out.printf("Monthly repayment: £%.2f%n", loanResult.getMonthlyRepayment());
            System.out.printf("Total repayment: £%.2f%n", loanResult.getTotalRepayment());
        } catch (MarketLiquidityException e) {
            System.out.println("It is not possible to provide the requested amount.");
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
