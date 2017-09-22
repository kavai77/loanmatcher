package com.kavai.zopa.loanmatcher.engine.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Lender {
    @CsvBindByName
    private String lender;
    @CsvBindByName(required = true)
    private double rate;
    @CsvBindByName(required = true)
    private double available;

    public Lender() {
    }

    public Lender(String lender, double rate, double available) {
        this.lender = lender;
        this.rate = rate;
        this.available = available;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lender lender1 = (Lender) o;
        return Double.compare(lender1.rate, rate) == 0 &&
                Double.compare(lender1.available, available) == 0 &&
                Objects.equals(lender, lender1.lender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lender, rate, available);
    }
}
