package com.muliamaulana.denius.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muliamaulana on 8/19/2018.
 */
public class Sources {

    @SerializedName("status")
    public String status;
    @SerializedName("sources")
    public List<AllSources> sources = null;

    public Sources(String status, List<AllSources> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public List<AllSources> getSources() {
        return sources;
    }
}
