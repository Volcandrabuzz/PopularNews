package com.example.myapplication.models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {


     String status;

    int totalResult;

    List<NewsHeadlines> article;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<NewsHeadlines> getArticle() {
        return article;
    }

    public void setArticle(List<NewsHeadlines> article) {
        this.article = article;
    }


}
