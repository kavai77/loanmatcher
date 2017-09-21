package com.kavai.zopa.loanmatcher.engine.model;

public class LoanInput {
    private final String marketFilePath;
    private final int requestedAmount;

    public LoanInput(String marketFilePath, int requestedAmount) {
        this.marketFilePath = marketFilePath;
        this.requestedAmount = requestedAmount;
    }

    public String getMarketFilePath() {
        return marketFilePath;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }
}
