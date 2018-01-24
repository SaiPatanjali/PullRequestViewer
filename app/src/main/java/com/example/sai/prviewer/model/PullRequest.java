package com.example.sai.prviewer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PullRequest implements Serializable {

    @SerializedName("title") private String title;

    @SerializedName("number") private int number;

    @SerializedName("created_at") private String createdAt;

    @SerializedName("user") private User user;

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserName() {
        return user.getName();
    }
}
