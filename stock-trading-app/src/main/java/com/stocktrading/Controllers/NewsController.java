package com.stocktrading.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stocktrading.model.News;
import com.stocktrading.service.NewsService;

import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String getNews(Model model) {
        List<News> newsList = newsService.getNews();
        model.addAttribute("newsList", newsList);
        return "news"; 
    }
}
