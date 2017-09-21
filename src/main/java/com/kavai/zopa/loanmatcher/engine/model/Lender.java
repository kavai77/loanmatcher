package com.kavai.zopa.loanmatcher.engine.model;

public class Lender {
    private String lender;
    private double rate;
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
}
