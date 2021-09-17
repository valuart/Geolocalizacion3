package com.example.geolocalizacion3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private double latitud = -33.2622607;
    private double longitud = -66.3020488;
    private receptorProximidad rp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validaPermisos();
        rp = new receptorProximidad();
        String codigo = "com.example.geolocalizacion3.ALERTA";
        //Mi app puede largar el siguiente intent
        Intent intent = new Intent(codigo);
        PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        manager.addProximityAlert(latitud, longitud, 500, - 1, pi);

        IntentFilter intentFilter = new IntentFilter(codigo);
        registerReceiver(rp, intentFilter);


    }

    private void validaPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 100);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(rp);
    }
}