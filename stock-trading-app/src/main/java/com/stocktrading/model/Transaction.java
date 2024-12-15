package com.stocktrading.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_symbol", nullable = false)
    private String stockSymbol;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String type; // "BUY" or "SELL"

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}






//package com.stocktrading.model;



//
//import java.time.LocalDateTime;
//import jakarta.persistence.*;
//
//@Entity
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    private String stockSymbol;
//    private int quantity;
//    private double price;
//    private LocalDateTime transactionTime;
//
//    // Constructor
//    public Transaction() {}
//
//    public Transaction(User user, String stockSymbol, int quantity, double price) {
//        this.user = user;
//        this.stockSymbol = stockSymbol;
//        this.quantity = quantity;
//        this.price = price;
//        this.transactionTime = LocalDateTime.now();  // Correct way to set current time
//    }
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public User getUser() { return user; }
//    public void setUser(User user) { this.user = user; }
//
//    public String getStockSymbol() { return stockSymbol; }
//    public void setStockSymbol(String stockSymbol) { this.stockSymbol = stockSymbol; }
//
//    public int getQuantity() { return quantity; }
//    public void setQuantity(int quantity) { this.quantity = quantity; }
//
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
//
//    public LocalDateTime getTransactionTime() { return transactionTime; }
//    public void setTransactionTime(LocalDateTime transactionTime) { 
//        this.transactionTime = LocalDateTime.now();  // Correct way to set current time
//    }
//}
