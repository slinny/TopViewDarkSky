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
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private CurrentWeather mCurrentWeather;
    private WeatherAdapter weatherAdapter;
    private LinearLayoutManager linearLayoutManager;
    String currentCityName;

    public static double latitude;
    public static double longitude;

    WeatherViewModel weatherViewModel;

    ImageView currentIconImageView;
    TextView currentCityTextView;
    TextView currentTempTextView;
    TextView noNetworkTextView;
    RecyclerView dailyRecyclerView;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getApplicationContext().getSharedPreferences(CITY_NAME_KEY, Context.MODE_PRIVATE);

        currentIconImageView = findViewById(R.id.current_icon_imageView);
        currentCityTextView = findViewById(R.id.city_textView);
        currentTempTextView = findViewById(R.id.temp_textview);

        dailyRecyclerView = findViewById(R.id.daily_recyclerview);

        noNetworkTextView = findViewById(R.id.no_network_textview);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
        Log.d("moclat", latitude + "");

        try {
            currentCityName = getCurrentCityName(latitude, longitude);
            currentCityTextView.setText(currentCityName);
        } catch (Exception e) {
            noNetworkTextView.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }


        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        weatherViewModel.getCurrentWeatherLiveData().observe(this, new Observer<CurrentWeather>() {
            @Override
            public void onChanged(@Nullable final CurrentWeather currentWeather) {
                mCurrentWeather = currentWeather;
                setCurrentIcon(mCurrentWeather.getIcon());
                currentTempTextView.setText(setCurrentTemp(mCurrentWeather.getTemperature()));
            }
        });

        weatherViewModel.getDailyWeatherLiveData().observe(this, new Observer<List<DailyWeatherData>>() {
            @Override
            public void onChanged(@Nullable final List<DailyWeatherData> dailyWeatherDataList) {
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

    private String getCurrentCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Log.d("mainlat", String.valueOf(latitude));

        } catch (IOException e) {
            e.printStackTrace();
        }
        String cityName = addresses.get(0).getLocality();
//        Log.d("mainCityName", cityName);
        return cityName;
    }
}

/*
1. databinding
2. unit and UI testing
5. dagger2
6. rxjava
 */