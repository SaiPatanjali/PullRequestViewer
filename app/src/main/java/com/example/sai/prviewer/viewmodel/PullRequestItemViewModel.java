package com.example.sai.prviewer.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.example.sai.prviewer.model.PullRequest;
import com.example.sai.prviewer.view.PullRequestActivity;

public class PullRequestItemViewModel extends BaseObservable{

    private PullRequest pullRequest;
    private Context context;

    public PullRequestItemViewModel(Context context, PullRequest pullRequest) {
        this.context = context;
        this.pullRequest = pullRequest;
    }

    public String getTitle() {
        return pullRequest.getTitle();
    }

    public String getDescription() {
        return "#" + String.valueOf(pullRequest.getNumber()) + " opened on "
                + pullRequest.getCreatedAt() + " by " + pullRequest.getUserName();
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        notifyChange();
    }

    public void onItemClick(View view) {
        context.startActivity(PullRequestActivity.launchIntent(view.getContext(), pullRequest));
    }
}
