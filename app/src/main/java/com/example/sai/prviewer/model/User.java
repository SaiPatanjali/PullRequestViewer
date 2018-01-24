package com.example.sai.prviewer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sai on 23/01/18.
 */

public class User implements Serializable {

    @SerializedName("login") private String name;

    public String getName() {
        return name;
    }
}
