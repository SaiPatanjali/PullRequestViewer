package com.example.sai.prviewer.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.sai.prviewer.PRViewer;
import com.example.sai.prviewer.data.PullRequestFactory;
import com.example.sai.prviewer.data.PullRequestService;
import com.example.sai.prviewer.model.PullRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PullRequestViewModel extends Observable {

    private Context context;
    public ObservableInt progressBarVisibility = new ObservableInt(View.GONE);
    public ObservableInt messageVisibility = new ObservableInt(View.GONE);
    public ObservableInt listVisibility = new ObservableInt(View.GONE);
    public ObservableField<String> messageText = new ObservableField<>();

    private List<PullRequest> pullRequestList = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PullRequestViewModel(@NonNull Context context) {
        this.context = context;
    }

    public void fetchPullRequests(final String repoName) {
        progressBarVisibility.set(View.VISIBLE);
        messageVisibility.set(View.GONE);
        listVisibility.set(View.GONE);

        PRViewer prViewer = PRViewer.create(context);
        PullRequestService pullRequestService = prViewer.getPullRequestService();

        Disposable disposable = pullRequestService
                .fetchPullRequests(PullRequestFactory.getUrl(repoName))
                .subscribeOn(prViewer.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PullRequest>>() {
                    @Override
                    public void accept(List<PullRequest> pullRequests) throws Exception {
                        progressBarVisibility.set(View.GONE);
                        messageVisibility.set(View.GONE);
                        listVisibility.set(View.VISIBLE);
                        updateList(pullRequests);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("error", throwable.getMessage());
                        messageText.set("repository " + repoName + "\ndoes not exist or is private");
                        progressBarVisibility.set(View.GONE);
                        messageVisibility.set(View.VISIBLE);
                        listVisibility.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void updateList(List<PullRequest> pullRequestList) {
        this.pullRequestList = pullRequestList;
        setChanged();
        notifyObservers();
    }

    public List<PullRequest> getPullRequests() {
        return pullRequestList;
    }
}
