package com.stocktrading.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stocktrading.model.PortfolioStock;
import com.stocktrading.model.Stock;
import com.stocktrading.model.TickerNSE;
import com.stocktrading.model.TickerUS;
import com.stocktrading.model.User;
import com.stocktrading.repository.PortfolioStockRepository;
import com.stocktrading.repository.StockRepository;
import com.stocktrading.repository.TickerNSERepository;
import com.stocktrading.repository.TickerUSRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.stocktrading.model.StockData_NSE;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PortfolioStockRepository portfolioStockRepository;
    
    
    @Autowired
    private TickerUSRepository tickerUSRepository;

    @Autowired
    private TickerNSERepository tickerNSERepository;

    
    private static final String API_URL = "https://indian-stock-exchange-api2.p.rapidapi.com/stock?name=";
    private static final String API_KEY = "demo";

    
    public Page<TickerUS> getUSStocks(String searchTerm, Pageable pageable) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return tickerUSRepository.findByTickerNameContainingIgnoreCase(searchTerm, pageable);
        }
        return tickerUSRepository.findAll(pageable);
    }

    
    public Page<TickerNSE> getNSEStocks(String searchTerm, Pageable pageable) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return tickerNSERepository.findByTickerNameContainingIgnoreCase(searchTerm, pageable);
        }
        return tickerNSERepository.findAll(pageable);
    }
    
    
    

    public StockData_NSE fetchStockData_NSE(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", API_KEY);
        headers.set("x-rapidapi-host", "indian-stock-exchange-api2.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<StockData_NSE> response = restTemplate.exchange(
                API_URL + symbol, HttpMethod.GET, entity, StockData_NSE.class);

        return response.getBody();
    }
    

    // Get all stocks
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public List<PortfolioStock> getPortfolio(Long userId) {
        // Logic to fetch the portfolio for the user
        return portfolioStockRepository.findByUserId(userId);
    }

    // Get stock by symbol
    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    // Get portfolio for a user
    public List<PortfolioStock> getPortfolio(User user) {
        return portfolioStockRepository.findByUser(user);
    }

    // Get stock price (mocked for now)
    public double getStockPrice(String stockSymbol) {
        Stock stock = stockRepository.findBySymbol(stockSymbol);
        if (stock != null) {
            return stock.getCurrentPrice(); 
        }
        return 0.0;
    }
    
    public TickerUS getUSStockBySymbol(String tickerSymbol) {
        return tickerUSRepository.findById(tickerSymbol).orElse(null);
    }

    public TickerNSE getNSEStockBySymbol(String tickerSymbol) {
        return tickerNSERepository.findById(tickerSymbol).orElse(null);
    }

    
}







//package com.stocktrading.service;
//
//import com.stocktrading.model.Stock;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class StockService {
//
//    public List<Stock> getAllStocks() {
//        List<Stock> stocks = new ArrayList<>();
//
//        // Mock stock data
//        stocks.add(new Stock("AAPL", "Apple Inc.", 175.75, 0.5));
//        stocks.add(new Stock("GOOGL", "Alphabet Inc.", 2800.50, -1.2));
//        stocks.add(new Stock("AMZN", "Amazon.com Inc.", 3400.20, 1.4));
//        stocks.add(new Stock("TSLA", "Tesla Inc.", 900.00, -0.9));
//
//        return stocks;
//    }
//    
//    public double getStockPrice(String stockSymbol) {
//        List<Stock> stocks = getAllStocks();
//        double getPriceValue = 0;
//        for (int i = 0; i < stocks.size(); i++) {
//            if (stocks.get(i).getSymbol().equals(stockSymbol)) {
//            	getPriceValue = stocks.get(i).getPrice();;
//                return getPriceValue;       
//            }
//            
//        }
//        return getPriceValue;
//      }
//    
//    
//    
//    }