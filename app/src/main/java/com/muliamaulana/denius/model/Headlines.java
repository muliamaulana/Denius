package com.muliamaulana.denius.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muliamaulana on 8/18/2018.
 */

public class Headlines {
    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public Integer totalResults;

    @SerializedName("articles")
    public List<Article> articles = null;

    public Headlines(String status, Integer totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
