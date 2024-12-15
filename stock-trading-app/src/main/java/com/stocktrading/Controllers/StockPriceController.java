package com.stocktrading.Controllers;

import com.stocktrading.model.Top_Stocks;
import com.stocktrading.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/topstocks")
    public String getTopStocks(Model model) {
        
    	stockPriceService.getTopGainersLosersTraded();

        
        List<Top_Stocks> topGainers = stockPriceService.getTopGainersStocks();
        List<Top_Stocks> topLosers = stockPriceService.getTopLosersStocks();
        List<Top_Stocks> topTraded = stockPriceService.getTopTradedStocks();
        //.stream().limit(3).collect(Collectors.toList());

       
        model.addAttribute("topGainers", topGainers);
        model.addAttribute("topLosers", topLosers);
        model.addAttribute("topTraded", topTraded);

        
        return "topstocks";
    }
}
