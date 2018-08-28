package com.muliamaulana.denius.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muliamaulana on 8/18/2018.
 */

public class Source {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
