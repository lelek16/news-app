package com.recruitment.consdata.news.http;

import com.recruitment.consdata.news.model.News;

import java.util.List;

public class ResponseDto {
    private String country;
    private String category;
    private List<News> articles;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
