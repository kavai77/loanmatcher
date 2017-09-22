package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.exception.InputValidationException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OpenCSVBasedParser implements MarketCSVParser {
    @Override
    public List<Lender> parseCsvMarketModelFile(String file) throws IOException, InputValidationException {
        try (FileReader reader = new FileReader(file)) {
            return new CsvToBeanBuilder(reader).withType(Lender.class).build().parse();
        } catch (RuntimeException e) {
            if (e.getCause() instanceof CsvException) {
                throw new InputValidationException(e.getCause().getMessage(), e);
            } else {
                throw e;
            }
        }
    }
}
