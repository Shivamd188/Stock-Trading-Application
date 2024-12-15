package com.stocktrading.model;

import jakarta.persistence.*;
 

@Entity
@Table(name = "tickers_us")
public class TickerUS {

    @Id
    @Column(name = "ticker", nullable = false)
    private String tickerSymbol;

    @Column(name = "company_name", nullable = true)
    private String tickerName;

    @Column(name = "industry", nullable = true)
    private String industryType;

    @Column(name = "market_cap", nullable = true)
    private String marketCap;

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public String getTickerName() {
		return tickerName;
	}

	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

    
    
}

