package com.example.peng.location_service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by peng on 12/7/15.
 */
public class TestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setCostAllowed(false);

        String provider = locationManager.getBestProvider(criteria, false);
        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(provider);

            MyLocationListener mylistener = new MyLocationListener();

            if (location != null) {
                // add location to the location listener for location changes
                mylistener.onLocationChanged(location);
            } else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

            // location updates: at least 1 meter and 500 milli seconds change
            locationManager.requestLocationUpdates(provider, 500, 1, mylistener);
        } catch (SecurityException e) {
            Log.e("SecurityException", e.getMessage());
        }
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Do something with the location
            Toast.makeText(getBaseContext(),  "Location changed!", Toast.LENGTH_SHORT).show();
            Log.i("Provider: ", location.getProvider());
            Log.i("Latitude: ", String.valueOf(location.getLatitude()));
            Log.i("Longitude: ", String.valueOf(location.getLongitude()));

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("onStatusChanged: ",  "Do something with the status: " + status );
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("onProviderEnabled: ", "Do something with the provider-> " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("onProviderDisabled:", "Do something with the provider-> " + provider);
        }
    }

}
