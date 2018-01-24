package com.example.sai.prviewer.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sai.prviewer.R;
import com.example.sai.prviewer.databinding.PullRequestActivityBinding;
import com.example.sai.prviewer.model.PullRequest;
import com.example.sai.prviewer.viewmodel.PullRequestDetailViewModel;

public class PullRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PullRequestActivityBinding pullRequestActivityBinding = DataBindingUtil
                .setContentView(this, R.layout.pull_request_activity);

        setSupportActionBar(pullRequestActivityBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        PullRequest pullRequest = (PullRequest) getIntent().getSerializableExtra("pull_request");
        PullRequestDetailViewModel pullRequestDetailViewModel = new PullRequestDetailViewModel(pullRequest);
        pullRequestActivityBinding.setPullRequestDetailViewModel(pullRequestDetailViewModel);
    }

    public static Intent launchIntent(Context context, PullRequest pullRequest) {
        Intent intent = new Intent(context, PullRequestActivity.class);
        intent.putExtra("pull_request", pullRequest);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
