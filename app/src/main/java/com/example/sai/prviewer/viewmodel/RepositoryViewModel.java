package com.example.sai.prviewer.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sai.prviewer.R;
import com.example.sai.prviewer.receiver.NetworkChangeReceiver;

import java.util.Observable;


public class RepositoryViewModel extends Observable{

    public ObservableField<String> repoName = new ObservableField<>("");
    private TextInputLayout repoNameTIL;
    private EditText repoNameET;
    public ObservableInt internetConnected = new ObservableInt(View.GONE);
    private Context context;

    @SuppressLint("ClickableViewAccessibility")
    public RepositoryViewModel(Context context) {
        this.context = context;
        setInternetConnected(NetworkChangeReceiver.isConnected(context));
        repoNameET = ((Activity) context).findViewById(R.id.repo_name_et);
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
                    if(name.length() > 0 && name.contains("/") && internetConnected.get() == View.GONE) {
                        String[] names = name.split("/");
                        if(names.length > 1) {
                            if (names[0].length() > 0 && names[1].length() > 0) {
                                return onSubmit();
                            }
                        }
                    }
                    repoNameTIL.setErrorEnabled(true);
                    repoNameTIL.setError("Provide valid :owner_name/:repo_name");
                }
                return false;
            }
        });

        repoNameET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (repoNameET.getRight() - repoNameET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        repoName.set("");
                    }
                }
                return false;
            }
        });
    }

    public String getName() {
        return repoName.get();
    }

    private boolean onSubmit() {
        repoNameTIL.setErrorEnabled(false);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(repoNameET.getWindowToken(), 0);
        }
        setChanged();
        notifyObservers();
        return false;
    }

    public void setInternetConnected(boolean connected) {
        internetConnected.set(connected ? View.GONE : View.VISIBLE);
    }
}
