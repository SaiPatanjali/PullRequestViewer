package com.example.sai.prviewer.data;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullRequestFactory {

    private static final String BASE_URL = "https://api.github.com/";

    public static PullRequestService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(PullRequestService.class);
    }

    public static String getUrl(String repoName) {
        String[] names = repoName.split("/");
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.github.com")
                .addPathSegment("repos")
                .addPathSegment(names[0])
                .addPathSegment(names[1])
                .addPathSegment("pulls")
                .addQueryParameter("state","open")
                .build();
        return url.toString();
    }
}
