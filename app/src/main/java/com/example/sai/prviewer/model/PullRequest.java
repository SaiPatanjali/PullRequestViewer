package com.example.sai.prviewer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PullRequest implements Serializable {

    @SerializedName("title") private String title;

    @SerializedName("number") private int number;

    @SerializedName("created_at") private String createdAt;

    @SerializedName("body") private String body;

    @SerializedName("user") private User user;

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public String getCreatedAt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date  = simpleDateFormat.parse(createdAt.substring(0, 10));
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return createdAt;
    }

    public String getUserName() {
        return user.getName();
    }

    public String getBody() {
        return body;
    }

    public String getUserImage() {
        return user.getImageUrl();
    }
}
