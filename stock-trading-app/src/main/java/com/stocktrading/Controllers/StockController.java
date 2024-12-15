package com.stocktrading.Controllers;
import com.stocktrading.model.TickerNSE;
import com.stocktrading.model.TickerUS;
import com.stocktrading.model.Transaction;
import com.stocktrading.model.User;
import com.stocktrading.repository.TransactionRepository;
import com.stocktrading.repository.UserRepository;
import com.stocktrading.service.StockPriceService;
import com.stocktrading.service.StockService;
import com.stocktrading.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stocktrading.service.StockService;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks/us")
    public String getUSStocks(
        @RequestParam(defaultValue = "") String searchTerm,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model
    ) {
        Page<TickerUS> stocks = stockService.getUSStocks(searchTerm, PageRequest.of(page, size));
        model.addAttribute("stocks", stocks.getContent());
        model.addAttribute("totalPages", stocks.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("searchTerm", searchTerm); 
        model.addAttribute("stockType", "us"); 
        return "stocks";
    }

    @GetMapping("/stocks/nse")
    public String getNSEStocks(
        @RequestParam(defaultValue = "") String searchTerm,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model
    ) {
        Page<TickerNSE> stocks = stockService.getNSEStocks(searchTerm, PageRequest.of(page, size));
        model.addAttribute("stocks", stocks.getContent());
        model.addAttribute("totalPages", stocks.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("searchTerm", searchTerm); 
        model.addAttribute("stockType", "nse"); 
        return "stocks";
    }

    
    @GetMapping("/stockinfo/us/{tickerSymbol}")
    public String getUSStockInfo(@PathVariable String tickerSymbol, Model model) {
        TickerUS stock = stockService.getUSStockBySymbol(tickerSymbol);
        model.addAttribute("stock", stock);
        return "stockinfo"; 
    }

    @GetMapping("/stockinfo/nse/{tickerSymbol}")
    public String getNSEStockInfo(@PathVariable String tickerSymbol, Model model) {
        TickerNSE stock = stockService.getNSEStockBySymbol(tickerSymbol);
        model.addAttribute("stock", stock);
        return "stockinfo"; 
    }

    
    
}










//package com.stocktrading.Controllers;



//
//import com.stocktrading.model.Stock;
//import com.stocktrading.model.Transaction;
//import com.stocktrading.model.User;
//import com.stocktrading.repository.TransactionRepository;
//import com.stocktrading.repository.UserRepository;
//import com.stocktrading.service.StockPriceService;
//import com.stocktrading.service.StockService;
//import com.stocktrading.service.UserService;
//
//import jakarta.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.security.Principal;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class StockController {
//	
//	
//	@Autowired
//    private StockService stockService;
//    @Autowired
//    private TransactionRepository transactionRepository;
//    @Autowired
//    private UserService userService;    
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private StockPriceService stockPriceService;
//   
//    @GetMapping("/stock-dashboard")
//    public String showStockDashboard(@RequestParam String symbol, Model model) {
//        double livePrice = stockPriceService.getLivePrice(symbol);
//        model.addAttribute("symbol", symbol);
//        model.addAttribute("livePrice", livePrice);
//        
//        String[] symbols = {"AMZN", "GOOG", "AAPL", "NFLX", "TSLA", "FB", "CSCO", "ORCL", "INTC", "QCOM", 
//                "EBAY", "DELL", "COST", "MSFT", "TWTR", "AABA", "SNAP", "AMD", "ATVI", "ZNGA"};
//
//        	// Map to hold symbol and live price
//        Map<String, Double> stockPrices = new HashMap<>();
//
//// Fetch live prices for each stock
//        for (String symbol1 : symbols) {
//        	double livePrice1 = stockPriceService.getLivePrice(symbol1);
//        	stockPrices.put(symbol1, livePrice1);
//        }
//
//model.addAttribute("stockPrices", stockPrices);
//return "stock_dashboard";
//        
//    }
//    
//    @GetMapping("/portfolio")
//    public String showPortfolio(HttpSession session, Model model) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//
//        if (loggedInUser == null) {
//            return "redirect:/login";  // Redirect to login if user is not logged in
//        }
//
//        // Fetch the user again from the database along with the transactions
//        User userWithTransactions = userRepository.findById(loggedInUser.getId()).orElse(null);
//
//        if (userWithTransactions != null) {
//            // Access the transactions collection to initialize it
//            List<Transaction> transactions = userWithTransactions.getTransactions();
//            model.addAttribute("transactions", transactions);
//        }
//
//        return "portfolio";  // Return the portfolio HTML page
//    }
//    @GetMapping("/stocks")
//    public String showStockList(Model model) {
//        List<Stock> stockList = stockService.getAllStocks();
//        model.addAttribute("stocks", stockList);
//        return "stock_dashboard"; // This corresponds to the Thymeleaf template
//    }
//    
//    @PostMapping("/buyStock")
//    public String buyStock(@RequestParam("stockSymbol") String stockSymbol, 
//                           @RequestParam("quantity") int quantity, 
//                           HttpSession session, Model model) {
//        // Fetch logged-in user from session
//        Object loggedInUser = session.getAttribute("loggedInUser");
//        if (loggedInUser == null) {
//            return "redirect:/login"; 
//        }
//
//        
//        double price = stockService.getStockPrice(stockSymbol);
//
//        
//        User user = (User) loggedInUser;
//
//        
//        Transaction transaction = new Transaction();
//        transaction.setStockSymbol(stockSymbol);
//        transaction.setQuantity(quantity);
//        transaction.setUser(user);
//        transaction.setPrice(price * quantity);
//
//        
//        transactionRepository.save(transaction);
//
//        model.addAttribute("message", "Stock purchased successfully!");
//
//        return "dashboard"; // Return to dashboard after purchase
//    }
//}