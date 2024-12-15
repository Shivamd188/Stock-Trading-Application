package com.stocktrading.Controllers;

import com.stocktrading.dto.UserSessionDTO;
import com.stocktrading.model.PortfolioStock;
import com.stocktrading.model.Transaction;
import com.stocktrading.model.User;
import com.stocktrading.service.StockService;
import com.stocktrading.service.TransactionService;
import com.stocktrading.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private StockService stockService;

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute("loggedInUser");
        if (userSessionDTO == null) {
            return "redirect:/login";
        }

        
        String email = userSessionDTO.getEmail();
        User user = userService.findByEmail(email); 

        if (user == null) {
            return "redirect:/login";
        }

       
        List<PortfolioStock> portfolio = stockService.getPortfolio(user);
        List<Transaction> recentTransactions = transactionService.getRecentTransactions(user);

        model.addAttribute("user", userSessionDTO);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("recentTransactions", recentTransactions);

        return "dashboard";
    }

}
