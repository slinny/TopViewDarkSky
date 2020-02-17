package com.example.android.topviewdarksky;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.ui.WeatherAdapter;
import com.example.android.topviewdarksky.ui.WeatherViewModel;
import com.example.android.topviewdarksky.util.WeatherIcons;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.android.topviewdarksky.util.Constants.CITY_NAME_KEY;
import static com.example.android.topviewdarksky.util.Constants.DEFAULT_CITY_NAME;
import static com.example.android.topviewdarksky.util.Constants.SHARED_PREFS;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    private List<DailyWeatherData> dailyWeatherDataArrayList = new ArrayList<>();
    private CurrentWeather currentWeather;
    private WeatherAdapter weatherAdapter;
    private LinearLayoutManager linearLayoutManager;
    String currentCityName;

    public static double latitude;
    public static double longitude;

    WeatherViewModel weatherViewModel;

    ImageView currentIconImageView;
    TextView currentCityTextView;
    TextView currentTempTextView;
    RecyclerView dailyRecyclerView;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getApplicationContext().getSharedPreferences(CITY_NAME_KEY,Context.MODE_PRIVATE);

        currentIconImageView = findViewById(R.id.current_icon_imageView);
        currentCityTextView = findViewById(R.id.city_textView);
        currentTempTextView = findViewById(R.id.temp_textview);

        dailyRecyclerView = findViewById(R.id.daily_recyclerview);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        Log.d("mainOClat", String.valueOf(latitude));

        if(latitude != 0.0 && longitude != 0.0) {
            currentCityName = getCurrentCityName(latitude, longitude);
            Log.d("mainCTN", currentCityName);
            currentCityTextView.setText(currentCityName);
            sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString(CITY_NAME_KEY,currentCityName).apply();
        }else if(sharedPreferences.getString(CITY_NAME_KEY,null) != null){
            currentCityName = sharedPreferences.getString(CITY_NAME_KEY,null);
            currentCityTextView.setText(currentCityName);
            latitude = 40.7128;
            longitude = -74.0060;
        }else{
            currentCityTextView.setText(DEFAULT_CITY_NAME);
            latitude = 40.7128;
            longitude = -74.0060;
        }


        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.getCurrentWeatherLiveData().observe(this, new Observer<CurrentWeather>() {
            @Override
            public void onChanged(CurrentWeather currentWeather) {
                Log.d("MainCT", currentWeather.getTemperature().toString());
                setCurrentIcon(currentWeather.getIcon());
                currentTempTextView.setText(setCurrentTemp(currentWeather.getTemperature()));
            }
        });

        weatherViewModel.getDailyWeatherLiveDataListWeatherLiveData().observe(this, new Observer<List<DailyWeatherData>>() {
            @Override
            public void onChanged(List<DailyWeatherData> dailyWeatherDataList) {
                Log.d("mainDaily0", dailyWeatherDataList.get(0).getTemperatureHigh().toString());
                dailyWeatherDataArrayList = dailyWeatherDataList;
                linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                dailyRecyclerView.setLayoutManager(linearLayoutManager);
                weatherAdapter = new WeatherAdapter((ArrayList<DailyWeatherData>) dailyWeatherDataArrayList);
                dailyRecyclerView.setAdapter(weatherAdapter);
            }
        });



    }

    private String setCurrentTemp(String temperature) {
        float tempInt = Float.valueOf(temperature);
        int tempR = Math.round(tempInt);
        String tempS = String.valueOf(tempR);
        return tempS + " \u2109";
    }

    private void setCurrentIcon(String resource) {
        Integer imageRsc = WeatherIcons.getIconResource(resource);
        this.currentIconImageView.setImageResource(Integer.valueOf(imageRsc));
    }

    private String getCurrentCityName(double latitude, double longitude){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Log.d("mainlat", String.valueOf(latitude));

        } catch (IOException e) {
            e.printStackTrace();
        }
        String cityName = addresses.get(0).getLocality();
        Log.d("mainCityName", cityName);
        return cityName;
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                    Log.d("mlat", location.getLatitude()+"");
                                    Log.d("mlon", location.getLongitude()+"");
//                                    latTextView.setText(location.getLatitude()+"");
//                                    lonTextView.setText(location.getLongitude()+"");
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            Log.d("mlastlat", mLastLocation.getLatitude()+"");
            Log.d("mlastlon", mLastLocation.getLongitude()+"");
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

}

/*
1. databinding
2. need to add code for room database in repository
5. dagger2
6. rxjava
 */