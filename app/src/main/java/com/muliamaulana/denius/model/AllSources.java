package com.muliamaulana.denius.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muliamaulana on 8/19/2018.
 */
public class AllSources {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("url")
    public String url;

    @SerializedName("category")
    public String category;

    @SerializedName("language")
    public String language;

    @SerializedName("country")
    public String country;

    public AllSources(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }
}
