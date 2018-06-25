package com.recruitment.consdata.news.downloader;

import java.util.List;

public class ResponseDto {
    private String status;
    private String code;
    private String message;
    private Integer totalResults;
    private List<ArticleReponseDto> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleReponseDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleReponseDto> articles) {
        this.articles = articles;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
