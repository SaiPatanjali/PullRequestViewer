package com.example.sai.prviewer.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.example.sai.prviewer.R;
import com.example.sai.prviewer.databinding.RepositoryActivityBinding;
import com.example.sai.prviewer.viewmodel.PullRequestViewModel;
import com.example.sai.prviewer.viewmodel.RepositoryViewModel;

import java.util.Observable;
import java.util.Observer;

public class RepositoryActivity extends AppCompatActivity implements Observer{

    private RepositoryActivityBinding repositoryActivityBinding;
    private RepositoryViewModel repositoryViewModel;
    private PullRequestViewModel pullRequestViewModel;
    private PullRequestAdapter pullRequestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeBinding();

        setSupportActionBar(repositoryActivityBinding.toolbar);

        pullRequestAdapter = new PullRequestAdapter(this);
        repositoryActivityBinding.list.setAdapter(pullRequestAdapter);
        repositoryActivityBinding.list.setLayoutManager(new LinearLayoutManager(this));
        repositoryActivityBinding.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initializeBinding() {
        repositoryActivityBinding = DataBindingUtil.setContentView(this, R.layout.repository_activity);

        repositoryViewModel = new RepositoryViewModel(this);
        repositoryActivityBinding.setRepositoryViewModel(repositoryViewModel);
        repositoryViewModel.addObserver(this);

        pullRequestViewModel = new PullRequestViewModel(this);
        repositoryActivityBinding.setPullRequestViewModel(pullRequestViewModel);
        pullRequestViewModel.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(observable instanceof PullRequestViewModel) {
            pullRequestAdapter.setPullRequestArrayList(pullRequestViewModel.getPullRequests());
        } else if(observable instanceof RepositoryViewModel) {
            pullRequestViewModel.fetchPullRequests(repositoryViewModel.getName());
        }
    }
}
