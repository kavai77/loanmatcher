package com.kavai.zopa.loanmatcher.engine;

import com.google.inject.AbstractModule;
import com.kavai.zopa.loanmatcher.engine.configuration.LoanNumberOfMonths;
import com.kavai.zopa.loanmatcher.engine.configuration.MaximumLoanAmount;
import com.kavai.zopa.loanmatcher.engine.configuration.MinimumAmountIncrement;
import com.kavai.zopa.loanmatcher.engine.configuration.MinimumLoanAmount;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class EngineModule extends AbstractModule {
    private static final String APP_PROPERTIES_FILE = "/app.properties";
    private static final String LOAN_NUMBER_OF_MONTHS_KEY = "loanNumberOfMonths";
    private static final String MINIMUM_LOAN_AMOUNT_KEY = "minimumLoanAmount";
    private static final String MAXIMUM_LOAN_AMOUNT_KEY = "maximumLoanAmount";
    private static final String MINIMUM_AMOUNT_INCREMENT_KEY = "minimumAmountIncrement";

    @Override
    protected void configure() {
        bind(InterestRateSolver.class).to(NewtonRaphsonInterestRateSolver.class);
        bind(LenderSelector.class).to(PriorityQueueBasedLenderSelector.class);
        bind(MarketCSVParser.class).to(OpenCSVBasedParser.class);
        loadAndBindConfiguration();
    }

    private void loadAndBindConfiguration() {
        Properties properties = new Properties();
        try (InputStream propertiesFileStream = getClass().getResourceAsStream(APP_PROPERTIES_FILE)) {
            properties.load(propertiesFileStream);
            bindConstant().annotatedWith(LoanNumberOfMonths.class).to(parseInt(properties.getProperty(LOAN_NUMBER_OF_MONTHS_KEY)));
            bindConstant().annotatedWith(MinimumLoanAmount.class).to(parseInt(properties.getProperty(MINIMUM_LOAN_AMOUNT_KEY)));
            bindConstant().annotatedWith(MaximumLoanAmount.class).to(parseInt(properties.getProperty(MAXIMUM_LOAN_AMOUNT_KEY)));
            bindConstant().annotatedWith(MinimumAmountIncrement.class).to(parseInt(properties.getProperty(MINIMUM_AMOUNT_INCREMENT_KEY)));
        } catch (IOException e) {
            throw new RuntimeException("Missing config file", e);
        }
    }
}
