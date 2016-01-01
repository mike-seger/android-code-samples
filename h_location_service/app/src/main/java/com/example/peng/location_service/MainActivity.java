package com.example.peng.location_service;

import android.os.Bundle;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView latitude;
    private TextView longitude;
    private TextView provText;
    private String provider;
    private RadioGroup accuracyChoice;

    private LocationManager locationManager;
    private MyLocationListener mylistener;
    private Criteria criteria;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accuracyChoice = (RadioGroup) findViewById(R.id.accuracy_choice);
        latitude = (TextView) findViewById(R.id.lat);
        longitude = (TextView) findViewById(R.id.lon);
        provText = (TextView) findViewById(R.id.prov);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);	//default
        accuracyChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.fine_accuracy) {
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);
                    Toast.makeText(MainActivity.this, "Fine Accuracy Selected!", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.coarse_accuracy) {
                    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                    Toast.makeText(MainActivity.this, "Coarse Accuracy Selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        criteria.setCostAllowed(false);
        // get the best provider depending on the criteria
        provider = locationManager.getBestProvider(criteria, false);

        Location location = null;
        try {
            // the last known location of this provider
            location = locationManager.getLastKnownLocation(provider);

            mylistener = new MyLocationListener();

            if (location != null) {
                mylistener.onLocationChanged(location);
            } else {
                // leads to the settings because there is no last known location
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

            // location updates: at least 1 meter and 200 millsecs change
            locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
        } catch (SecurityException e) {
            Log.e("SecurityException", e.getMessage());
        }

    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Initialize the location fields
            provText.setText("Location Provider: " + provider);
            latitude.setText("Latitude: " + String.valueOf(location.getLatitude()));
            longitude.setText("Longitude: " + String.valueOf(location.getLongitude()));
            Toast.makeText(MainActivity.this,  "Location changed!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(MainActivity.this, provider + "'s status changed to " + status + "!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(MainActivity.this, "Provider " + provider + " enabled!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(MainActivity.this, "Provider " + provider + " disabled!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}