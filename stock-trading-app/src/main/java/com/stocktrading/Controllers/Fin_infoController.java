package com.stocktrading.Controllers;

import com.stocktrading.service.StockService;
import com.stocktrading.model.StockData_NSE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Fin_infoController { 

    @Autowired
    private StockService stockService;

    @GetMapping("/ticker_nse")

    public String getStockDetails(@RequestParam("symbol") String symbol, Model model) {
        StockData_NSE stockData = stockService.fetchStockData_NSE(symbol);
        model.addAttribute("stockData", stockData);
        return "stockDetails";
    }
}
