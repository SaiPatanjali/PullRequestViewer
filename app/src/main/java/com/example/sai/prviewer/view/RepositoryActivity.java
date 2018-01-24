package com.example.sai.prviewer.view;

import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import com.example.sai.prviewer.R;
import com.example.sai.prviewer.databinding.RepositoryActivityBinding;
import com.example.sai.prviewer.model.NetworkConnection;
import com.example.sai.prviewer.receiver.NetworkChangeReceiver;
import com.example.sai.prviewer.viewmodel.PullRequestViewModel;
import com.example.sai.prviewer.viewmodel.RepositoryViewModel;

import java.util.Observable;
import java.util.Observer;

public class RepositoryActivity extends AppCompatActivity implements Observer{

    private RepositoryActivityBinding repositoryActivityBinding;
    private RepositoryViewModel repositoryViewModel;
    private PullRequestViewModel pullRequestViewModel;
    private PullRequestAdapter pullRequestAdapter;
    private NetworkChangeReceiver networkChangeReceiver;
    private NetworkConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connection = new NetworkConnection(this);
        connection.addObserver(this);
        networkChangeReceiver = new NetworkChangeReceiver(connection);

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
        } else if(observable instanceof NetworkConnection){
            repositoryViewModel.setInternetConnected(connection.isConnected());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}

