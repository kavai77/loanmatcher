package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.model.Lender;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OpenCSVBasedParser implements MarketCSVParser {
    @Override
    public List<Lender> parseCsvMarketModelFile(String file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            return new CsvToBeanBuilder(reader).withType(Lender.class).build().parse();
        }
    }
}
