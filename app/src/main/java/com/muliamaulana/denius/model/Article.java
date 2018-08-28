package com.muliamaulana.denius.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muliamaulana on 8/18/2018.
 */
public class Article {
    @SerializedName("source")
    public Source source;

    @SerializedName("author")
    public String author;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("url")
    public String url;

    @SerializedName("urlToImage")
    public String urlToImage;

    @SerializedName("publishedAt")
    public String publishedAt;

    public Article() {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
