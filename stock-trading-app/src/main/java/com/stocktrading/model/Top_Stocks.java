package com.stocktrading.model;

public class Top_Stocks {
    private String ticker;
    private String price;
    private String changeAmount;
    private String changePercentage;
    private String volume;

    public Top_Stocks(String ticker, String price, String changeAmount, String changePercentage, String volume) {
        this.ticker = ticker;
        this.price = price;
        this.changeAmount = changeAmount;
        this.changePercentage = changePercentage;
        this.volume = volume;
    }

    // Getters and Setters
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(String changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangePercentage() {
        return changePercentage;
    }

    public void setChangePercentage(String changePercentage) {
        this.changePercentage = changePercentage;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}