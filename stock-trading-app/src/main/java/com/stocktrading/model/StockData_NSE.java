package com.stocktrading.model;

public class StockData_NSE {

    private String companyName;
    private String industry;
    private double currentPrice;
    private double yearHigh;
    private double yearLow;
    private double percentChange;
    private String description;

    

    // Getters and Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(double yearHigh) {
        this.yearHigh = yearHigh;
    }

    public double getYearLow() {
        return yearLow;
    }

    public void setYearLow(double yearLow) {
        this.yearLow = yearLow;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
