package com.example.sai.prviewer.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sai.prviewer.R;

import java.util.Observable;


public class RepositoryViewModel extends Observable{

    public ObservableField<String> repoName = new ObservableField<>();
    private TextInputLayout repoNameTIL;

    public RepositoryViewModel(Context context) {

        EditText repoNameET = ((Activity) context).findViewById(R.id.repo_name_et);
        repoNameTIL = ((Activity) context).findViewById(R.id.repo_name_til);

        repoNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                repoName.set(s.toString());
            }
        });

        repoNameET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO) {
                    String name = repoName.get();
                    if(name.length() > 0 && name.contains("/")) {
                        String[] names = name.split("/");
                        if(names.length > 1) {
                            if (names[0].length() > 0 && names[1].length() > 0) {
                                repoNameTIL.setErrorEnabled(false);
                                onSubmit();
                                return false;
                            }
                        }
                    }
                    repoNameTIL.setErrorEnabled(true);
                    repoNameTIL.setError("Provide valid :owner_name/:repo_name");
                }
                return false;
            }
        });
    }

    public String getName() {
        return repoName.get();
    }

    private void onSubmit() {
        setChanged();
        notifyObservers();
    }
}
