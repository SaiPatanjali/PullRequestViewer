package com.example.sai.prviewer.model;

import android.content.Context;

import com.example.sai.prviewer.receiver.NetworkChangeReceiver;

import java.util.Observable;

public class NetworkConnection extends Observable {
    private boolean connected;

    public NetworkConnection(Context context) {
        connected = NetworkChangeReceiver.isConnected(context);
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        setChanged();
        notifyObservers();
    }
}
