package com.example.sai.prviewer.data;

import com.example.sai.prviewer.model.PullRequest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PullRequestService {

    @GET Observable<List<PullRequest>> fetchPullRequests(@Url String url);
}
