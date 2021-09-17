package com.example.geolocalizacion3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class receptorProximidad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"dentro del area", Toast.LENGTH_LONG).show();
    }
}
