package com.example.sai.prviewer.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.sai.prviewer.model.PullRequest;
import com.squareup.picasso.Picasso;

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

    public String getBody() {
        return pullRequest.getBody();
    }

    public String getOpenedAt() {
        return "Opened on " + pullRequest.getCreatedAt();
    }

    public String getNumber() {
        return "#" + String.valueOf(pullRequest.getNumber());
    }

    public String getImageUrl() {
        return pullRequest.getUserImage();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext()).load(imageUrl).into(view);
    }
}
