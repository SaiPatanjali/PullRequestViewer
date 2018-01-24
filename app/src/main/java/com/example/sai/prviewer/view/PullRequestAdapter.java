package com.example.sai.prviewer.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sai.prviewer.R;
import com.example.sai.prviewer.databinding.ItemPullRequestBinding;
import com.example.sai.prviewer.model.PullRequest;
import com.example.sai.prviewer.viewmodel.PullRequestItemViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>{

    private List<PullRequest> pullRequestArrayList;
    private Context context;

    public PullRequestAdapter(Context context) {
        this.context = context;
        this.pullRequestArrayList = Collections.emptyList();
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPullRequestBinding itemPullRequestBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_pull_request,
                        parent, false);
        return new PullRequestViewHolder(itemPullRequestBinding);
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        holder.bindPullRequest(context, pullRequestArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return pullRequestArrayList.size();
    }

    public void setPullRequestArrayList(List<PullRequest> pullRequestArrayList) {
        this.pullRequestArrayList = pullRequestArrayList;
        notifyDataSetChanged();
    }

    public static class PullRequestViewHolder extends RecyclerView.ViewHolder {

        ItemPullRequestBinding itemPullRequestBinding;

        public PullRequestViewHolder(ItemPullRequestBinding itemPullRequestBinding) {
            super(itemPullRequestBinding.itemPullRequest);
            this.itemPullRequestBinding = itemPullRequestBinding;
        }

        public void bindPullRequest(Context context, PullRequest pullRequest) {
            if(itemPullRequestBinding.getPullRequestItemViewModel() == null) {
                itemPullRequestBinding.setPullRequestItemViewModel(new PullRequestItemViewModel(context, pullRequest));
            } else {
                itemPullRequestBinding.getPullRequestItemViewModel().setPullRequest(pullRequest);
            }
        }
    }
}
