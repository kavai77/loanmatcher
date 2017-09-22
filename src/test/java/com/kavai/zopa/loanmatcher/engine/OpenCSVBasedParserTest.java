package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.exception.InputValidationException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OpenCSVBasedParserTest {
    private OpenCSVBasedParser sut;

    @Before
    public void setUp() throws Exception {
        sut = new OpenCSVBasedParser();
    }

    @Test
    public void loadCorrectCsv() throws Exception {
        final String csvPath = new File(getClass().getResource("/correct_marketdata.csv").toURI()).getAbsolutePath();
        final List<Lender> lenders = sut.parseCsvMarketModelFile(csvPath);
        assertEquals(Arrays.asList(
                new Lender("Bob", 0.075, 640),
                new Lender("Edith", 0.08, 320)),
            lenders);
    }

    @Test(expected = InputValidationException.class)
    public void loadIncorrectCsv() throws Exception {
        final String csvPath = new File(getClass().getResource("/incorrect_marketdata.csv").toURI()).getAbsolutePath();
        sut.parseCsvMarketModelFile(csvPath);
    }
}
