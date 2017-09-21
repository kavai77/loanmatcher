package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.exception.MarketLiquidityException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;

import java.util.List;

public interface LenderSelector {
    List<Lender> selectBestRateLenders(List<Lender> lenderList, double requestedAmount) throws MarketLiquidityException;
}
