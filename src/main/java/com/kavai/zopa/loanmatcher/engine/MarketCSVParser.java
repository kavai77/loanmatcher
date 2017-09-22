package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.exception.InputValidationException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;

import java.io.IOException;
import java.util.List;

public interface MarketCSVParser {
    List<Lender> parseCsvMarketModelFile(String file) throws IOException, InputValidationException;
}
