package com.example.sai.prviewer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.sai.prviewer.PRViewer;
import com.example.sai.prviewer.model.NetworkConnection;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkConnection connection;

    public NetworkChangeReceiver(NetworkConnection connection) {
        this.connection = connection;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        PRViewer prViewer = PRViewer.create(context);
        if (wifi.isConnectedOrConnecting() || mobile.isConnectedOrConnecting()) {
//            prViewer.setConnectionChanged(true);
            connection.setConnected(true);
        } else {
//            prViewer.setConnectionChanged(false);
            connection.setConnected(false);
        }
    }

}
