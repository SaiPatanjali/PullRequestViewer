package com.example.sai.prviewer;

import android.app.Application;
import android.content.Context;

import com.example.sai.prviewer.data.PullRequestFactory;
import com.example.sai.prviewer.data.PullRequestService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class PRViewer extends Application {

    private PullRequestService pullRequestService;
    private Scheduler scheduler;

    private static PRViewer get(Context context){
        return (PRViewer) context.getApplicationContext();
    }

    public static PRViewer create(Context context) {
        return PRViewer.get(context);
    }

    public PullRequestService getPullRequestService() {
        if(pullRequestService == null) {
            pullRequestService = PullRequestFactory.create();
        }
        return pullRequestService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

    public void setPullRequestService(PullRequestService pullRequestService) {
        this.pullRequestService = pullRequestService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
