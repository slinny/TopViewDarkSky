package com.example.android.topviewdarksky.util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Do something with the location
//            Toast.makeText(getBaseContext(),  "Location changed!", Toast.LENGTH_SHORT).show();
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