package com.stocktrading.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stocktrading.model.PortfolioStock;
import com.stocktrading.model.User;
import com.stocktrading.service.StockService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private StockService stockService;

    @GetMapping("/portfolio")
    public List<PortfolioStock> getPortfolio(HttpSession session) {
        // Retrieve the logged-in user's data from the session
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not logged in");
        }

        
        Long userId = user.getId();
        return stockService.getPortfolio(userId);
    }
}
