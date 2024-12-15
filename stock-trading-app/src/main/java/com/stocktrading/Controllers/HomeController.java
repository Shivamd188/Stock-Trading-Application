package com.stocktrading.Controllers;
import java.util.stream.Collectors;

import com.stocktrading.service.NewsService;
import com.stocktrading.service.StockPriceService;
import com.stocktrading.service.StockService;
import com.stocktrading.service.TransactionService;

import com.stocktrading.service.UserService;
import com.stocktrading.model.User;
import com.stocktrading.model.News;
import com.stocktrading.model.PortfolioStock;
import com.stocktrading.model.Stock;
import com.stocktrading.model.Top_Stocks;
import com.stocktrading.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @Autowired
    private StockPriceService stockPriceService;

    @Autowired
    private StockService stockService;
    
    @Autowired
    private TransactionService transactionService;
    
    
    @GetMapping("/")
    public String home(Model model) {
       
        stockPriceService.getTopGainersLosersTraded();

        
        List<News> newsList = newsService.getNews(); // Directly get the news list

        
        List<Top_Stocks> topGainers = stockPriceService.getTopGainersStocks().stream().limit(3).collect(Collectors.toList());
        List<Top_Stocks> topLosers = stockPriceService.getTopLosersStocks().stream().limit(3).collect(Collectors.toList());
        List<Top_Stocks> topTraded = stockPriceService.getTopTradedStocks().stream().limit(3).collect(Collectors.toList());
        
        
        List<News> limitedNewsList = newsList.stream().limit(3).collect(Collectors.toList());
        
        
        model.addAttribute("topGainers", topGainers);
        model.addAttribute("topLosers", topLosers);
        model.addAttribute("topTraded", topTraded);
        model.addAttribute("newsList", limitedNewsList);

        return "index";
    }


//    @GetMapping("/dashboard")
//    public String showDashboard(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        
//        List<PortfolioStock> portfolio = stockService.getPortfolio(user);
//        List<Transaction> recentTransactions = transactionService.getRecentTransactions(user);
//
//        model.addAttribute("user", user);
//        model.addAttribute("portfolio", portfolio);
//        model.addAttribute("recentTransactions", recentTransactions);
//
//        return "dashboard";
//    }


}


    
//    @GetMapping("/dashboard")
//    public String showDashboard(HttpSession session, Model model) {
//        if (model.getAttribute("loggedInUser") != null) {
//            return "dashboard";
//        } else {
//            return "redirect:/login";
//        }
//    }
//
//    // Logout
//    @GetMapping("/logout")
//    public String logout(SessionStatus sessionStatus) {
//        sessionStatus.setComplete();
//        return "login";
//    }

    // Profile Page
//    @GetMapping("/profile")
//    public String showProfile(HttpSession session, Model model) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            model.addAttribute("user", loggedInUser);
//            return "profile";
//        } else {
//            return "redirect:/login";
//        }
//    }

    
//    @PostMapping("/profile")
//    public String updateProfile(@ModelAttribute("user") User user, HttpSession session) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            loggedInUser.setFirstName(user.getFirstName());
//            loggedInUser.setLastName(user.getLastName());
//            
//            userService.updateUser(loggedInUser);
//            session.setAttribute("loggedInUser", loggedInUser);
//            return "redirect:/dashboard";
//        }
//        return "redirect:/login";
//    }
