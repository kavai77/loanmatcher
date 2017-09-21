package com.kavai.zopa.loanmatcher.engine;

import com.google.inject.Inject;
import com.kavai.zopa.loanmatcher.engine.configuration.MaximumLoanAmount;
import com.kavai.zopa.loanmatcher.engine.configuration.MinimumAmountIncrement;
import com.kavai.zopa.loanmatcher.engine.configuration.MinimumLoanAmount;
import com.kavai.zopa.loanmatcher.engine.exception.InputValidationException;
import com.kavai.zopa.loanmatcher.engine.model.LoanInput;

import java.io.File;

public class InputValidator {
    private final int minimumLoanAmount;
    private final int maximumLoanAmount;
    private final int minimumAmountIncrement;

    @Inject
    public InputValidator(@MinimumLoanAmount int minimumLoanAmount,
                          @MaximumLoanAmount int maximumLoanAmount,
                          @MinimumAmountIncrement int minimumAmountIncrement) {
        this.minimumLoanAmount = minimumLoanAmount;
        this.maximumLoanAmount = maximumLoanAmount;
        this.minimumAmountIncrement = minimumAmountIncrement;
    }

    public void validateInput(LoanInput loanInput) throws InputValidationException {
        if (!new File(loanInput.getMarketFilePath()).exists()) {
            throw new InputValidationException("The market file does not exists:" + loanInput.getMarketFilePath());
        }
        if (loanInput.getRequestedAmount() < minimumLoanAmount) {
            throw new InputValidationException("The requested amount is too small. The minimum amount is £" + minimumLoanAmount);
        }

        if (loanInput.getRequestedAmount() > maximumLoanAmount) {
            throw new InputValidationException("The requested amount is too large. The maximum amount is £" + maximumLoanAmount);
        }

        if (loanInput.getRequestedAmount() % minimumAmountIncrement != 0) {
            throw new InputValidationException("The requested amount should be a multiplier of £" + minimumAmountIncrement);
        }
    }
}
