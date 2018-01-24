package com.example.sai.prviewer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("login") private String name;

    @SerializedName("avatar_url") private String imageUrl;

    public String getName() {
        return name;
    }

    String getImageUrl() {
        return imageUrl;
    }
}
