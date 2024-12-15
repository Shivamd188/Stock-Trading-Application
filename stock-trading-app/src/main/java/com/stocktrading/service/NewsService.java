package com.stocktrading.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stocktrading.model.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




//@Service
//public class NewsService {
//
//    private static final String NEWS_API_URL = "https://api.stockdata.org/v1/news/all?symbols=TSLA,AMZN,MSFT&filter_entities=true&language=en&api_token=uRW7mYHDnip79f6qvYNHM7pFmAvhOhVGFrcDZPv5"; // Replace with your API endpoint
//    private List<News> newsList = new ArrayList<>();
//
//    public void fetchNews() {
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            // Fetch JSON response
//            String jsonResponse = restTemplate.getForObject(NEWS_API_URL, String.class);
//
//            // Parse the JSON response
//            JSONObject response = new JSONObject(jsonResponse);
//            System.out.println("Response JSON: " + response.toString(2));
//            
//            
//            JSONArray dataArray = response.getJSONArray("data");
//            System.out.println("Data Array: " + dataArray.toString(2));
//
//            // Clear existing news
//            newsList.clear();
//
//            // Extract relevant fields from each news object
//            for (int i = 0; i < dataArray.length(); i++) {
//                JSONObject newsJson = dataArray.getJSONObject(i);
//                String title = newsJson.getString("title");
//                String description = newsJson.getString("description");
//                String url = newsJson.getString("url");
//                String imageUrl = newsJson.optString("image_url", null);
//                String publishedAt = newsJson.getString("published_at");
//                String source = newsJson.getString("source");
//
//                // Add to the list
//                newsList.add(new News(title, description, url, imageUrl, publishedAt, source));
//            }
//        } catch (Exception e) {
//            System.err.println("Error fetching or parsing news data: " + e.getMessage());
//        }
//    }
//
//    public List<News> getNews() {
//        return newsList;
//    }
//}
//


//@Service
//public class NewsService {
//
//    private static final String NEWS_API_URL = "https://api.stockdata.org/v1/news/all?industries=Technology&api_token=myapikey"; // Replace with your API key
//    private List<Article> articles = new ArrayList<>();
//
//    public void fetchNews() {
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            // Fetch JSON response
//            String jsonResponse = restTemplate.getForObject(NEWS_API_URL, String.class);
//
//            // Parse the JSON response
//            JSONObject response = new JSONObject(jsonResponse);
//            JSONArray dataArray = response.getJSONArray("data");
//
//            // Clear previous data
//            articles.clear();
//
//            // Parse main articles and their related articles
//            for (int i = 0; i < dataArray.length(); i++) {
//                JSONObject newsJson = dataArray.getJSONObject(i);
//
//                // Extract main article details
//                String uuid = newsJson.getString("uuid");
//                String title = newsJson.getString("title");
//                String description = newsJson.getString("description");
//                String url = newsJson.getString("url");
//                String imageUrl = newsJson.optString("image_url", null);
//                String publishedAt = newsJson.getString("published_at");
//                String source = newsJson.getString("source");
//
//                // Parse related articles (similar)
//                List<Article> relatedArticles = new ArrayList<>();
//                JSONArray similarArray = newsJson.optJSONArray("similar");
//                if (similarArray != null) {
//                    for (int j = 0; j < similarArray.length(); j++) {
//                        JSONObject similarJson = similarArray.getJSONObject(j);
//                        String similarTitle = similarJson.getString("title");
//                        String similarDescription = similarJson.getString("description");
//                        String similarUrl = similarJson.getString("url");
//                        String similarImageUrl = similarJson.optString("image_url", null);
//                        String similarPublishedAt = similarJson.getString("published_at");
//                        String similarSource = similarJson.getString("source");
//
//                        // Add related article to the list
//                        relatedArticles.add(new Article(similarTitle, similarDescription, similarUrl, similarImageUrl, similarPublishedAt, similarSource, null));
//                    }
//                }
//
//                // Add main article with related articles
//                articles.add(new Article(title, description, url, imageUrl, publishedAt, source, relatedArticles));
//            }
//        } catch (Exception e) {
//            System.err.println("Error fetching or parsing news data: " + e.getMessage());
//        }
//    }
//
//    public List<Article> getArticles() {
//        return articles;
//    }
//
//    // Article Class
//    public static class Article {
//        private String title;
//        private String description;
//        private String url;
//        private String imageUrl;
//        private String publishedAt;
//        private String source;
//        private List<Article> relatedArticles;
//
//        public Article(String title, String description, String url, String imageUrl, String publishedAt, String source, List<Article> relatedArticles) {
//            this.title = title;
//            this.description = description;
//            this.url = url;
//            this.imageUrl = imageUrl;
//            this.publishedAt = publishedAt;
//            this.source = source;
//            this.relatedArticles = relatedArticles;
//        }
//
//        // Getters and toString method
//        @Override
//        public String toString() {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Title: ").append(title)
//              .append("\nDescription: ").append(description)
//              .append("\nURL: ").append(url)
//              .append("\nImage URL: ").append(imageUrl)
//              .append("\nPublished At: ").append(publishedAt)
//              .append("\nSource: ").append(source)
//              .append("\nRelated Articles:\n");
//            if (relatedArticles != null) {
//                for (Article related : relatedArticles) {
//                    sb.append("\t- ").append(related.title).append(" (").append(related.source).append(")\n");
//                }
//            }
//            sb.append("\n");
//            return sb.toString();
//        }
//    }
//}




@Service
public class NewsService {

    private final String API_URL = "demo"; // "https://api.stockdata.org/v1/news/all?industries=Technology,Healthcare,Finance&api_token=uRW7mYHDnip79f6qvYNHM7pFmAvhOhVGFrcDZPv5
   
    private final List<News> newsList = new ArrayList<>();

    public List<News> getNews() {
        newsList.clear(); // Clear the list to avoid duplicate entries
        try {
            String url = API_URL;
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Add main news articles
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject newsJson = dataArray.getJSONObject(i);
                addNewsToList(newsJson);

                // Add similar news articles
                JSONArray similarArray = newsJson.optJSONArray("similar");
                if (similarArray != null) {
                    for (int j = 0; j < similarArray.length(); j++) {
                        addNewsToList(similarArray.getJSONObject(j));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching news: " + e.getMessage());
        }

        return newsList;
    }

    private void addNewsToList(JSONObject newsJson) {
        try {
            String title = newsJson.optString("title", "No Title");
            String description = newsJson.optString("description", "No Description");
            String url = newsJson.optString("url", "#");
            String imageUrl = newsJson.optString("image_url", null);
            String source = newsJson.optString("source", "Unknown");
            String publishedAt = newsJson.optString("published_at", "Unknown");

            newsList.add(new News(title, description, url, imageUrl, source, publishedAt));
        } catch (Exception e) {
            System.err.println("Error adding news to list: " + e.getMessage());
        }
    }
}
