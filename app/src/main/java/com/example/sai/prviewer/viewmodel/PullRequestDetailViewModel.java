package com.example.sai.prviewer.viewmodel;

import com.example.sai.prviewer.model.PullRequest;

/**
 * Created by sai on 24/01/18.
 */

public class PullRequestDetailViewModel {

    private PullRequest pullRequest;

    public PullRequestDetailViewModel(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public String getTitle() {
        return pullRequest.getTitle();
    }

    public String getUserName() {
        return pullRequest.getUserName();
    }

}
