package com.stocktrading.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.stocktrading.model.Top_Stocks;

@Service
public class StockPriceService {

    private final String API_KEY = "demo";  
    private final String BASE_URL = "https://www.alphavantage.co/query?function=";
    private final String Url_TimeSeriesFunction = "TIME_SERIES_INTRADAY";
    private final String Url_TopGainerFunction = "TOP_GAINERS_LOSERS";
    
    private List<Top_Stocks> topGainersStocks = new ArrayList<>();
    private List<Top_Stocks> topLosersStocks = new ArrayList<>();
    private List<Top_Stocks> topTradedStocks = new ArrayList<>();
    

 //   https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=1U1TPJ66G5EK
    
    public double getLivePrice(String stockSymbol) {
        String url = BASE_URL+ Url_TimeSeriesFunction + "&symbol=" + stockSymbol + "&interval=5min&apikey=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        

        // Parse the JSON response
        JSONObject jsonObj = new JSONObject(result);
        JSONObject timeSeries = jsonObj.getJSONObject("Time Series (5min)");

        // Get the most recent time's data
        Iterator<String> keys = timeSeries.keys();
        String latestTime = keys.next();  
        double latestPrice = timeSeries.getJSONObject(latestTime).getDouble("1. open");

        return latestPrice;
    }
    
    //String Base_Url2 = "https://www.alphavantage.co/query?function=TOP_GAINERS_LOSERS&apikey=demo";
    
    
    public void getTopGainersLosersTraded() {
        String url = BASE_URL + Url_TopGainerFunction + "&apikey=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();

        try {
            String result = restTemplate.getForObject(url, String.class);
            if (result == null || result.isEmpty()) {
                System.out.println("API response is empty or null.");
                return;
            }

            JSONObject jsonObj = new JSONObject(result);

            // Clear existing data to prevent duplication
            topGainersStocks.clear();
            topLosersStocks.clear();
            topTradedStocks.clear();

            // 1. Extract top gainers
            JSONArray topGainersArray = jsonObj.optJSONArray("top_gainers");
            if (topGainersArray != null) {
                for (int i = 0; i < topGainersArray.length(); i++) {
                    JSONObject stockObj = topGainersArray.getJSONObject(i);
                    Top_Stocks stock = new Top_Stocks(
                            stockObj.getString("ticker"),
                            stockObj.getString("price"),
                            stockObj.getString("change_amount"),
                            stockObj.getString("change_percentage"),
                            stockObj.getString("volume")
                    );
                    topGainersStocks.add(stock);
                }
            }

            // 2. Extract top losers
            JSONArray topLosersArray = jsonObj.optJSONArray("top_losers");
            if (topLosersArray != null) {
                for (int i = 0; i < topLosersArray.length(); i++) {
                    JSONObject stockObj = topLosersArray.getJSONObject(i);
                    Top_Stocks stock = new Top_Stocks(
                            stockObj.getString("ticker"),
                            stockObj.getString("price"),
                            stockObj.getString("change_amount"),
                            stockObj.getString("change_percentage"),
                            stockObj.getString("volume")
                    );
                    topLosersStocks.add(stock);
                }
            }

            // 3. Extract most traded stocks
            JSONArray topTradedArray = jsonObj.optJSONArray("most_actively_traded");
            if (topTradedArray != null) {
                for (int i = 0; i < topTradedArray.length(); i++) {
                    JSONObject stockObj = topTradedArray.getJSONObject(i);
                    Top_Stocks stock = new Top_Stocks(
                            stockObj.getString("ticker"),
                            stockObj.getString("price"),
                            stockObj.getString("change_amount"),
                            stockObj.getString("change_percentage"),
                            stockObj.getString("volume")
                    );
                    topTradedStocks.add(stock);
                }
            }

        } catch (Exception e) {
            System.err.println("Error fetching data from API: " + e.getMessage());
        }
    }

    // Getter methods for the lists
    public List<Top_Stocks> getTopGainersStocks() {
        return topGainersStocks;
    }

    public List<Top_Stocks> getTopLosersStocks() {
        return topLosersStocks;
    }

    public List<Top_Stocks> getTopTradedStocks() {
        return topTradedStocks;
    }
    
    
    
    public double getLivePrice2(String stockSymbol) {
        String url = BASE_URL + "&symbol=" + stockSymbol + "&interval=5min&apikey=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        // Parse the JSON response
        JSONObject jsonObj = new JSONObject(result);
        JSONObject timeSeries = jsonObj.getJSONObject("Time Series (5min)");

        // Get the most recent time's data
        Iterator<String> keys = timeSeries.keys();
        String latestTime = keys.next();  // This will give you the most recent timestamp
        double latestPrice = timeSeries.getJSONObject(latestTime).getDouble("1. open");

        return latestPrice;
    }
}