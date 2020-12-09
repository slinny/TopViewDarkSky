package com.example.android.topviewdarksky;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.topviewdarksky.databinding.ActivityMainBinding;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.ui.WeatherAdapter;
import com.example.android.topviewdarksky.ui.WeatherViewModel;
import com.example.android.topviewdarksky.util.WeatherIcons;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.android.topviewdarksky.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    private List<DailyWeatherData> dailyWeatherDataArrayList = new ArrayList<>();
    private CurrentWeather mCurrentWeather;
    private WeatherAdapter weatherAdapter;
    private LinearLayoutManager linearLayoutManager;
    String currentCityName;
    private View view;

    public static double latitude;
    public static double longitude;

    WeatherViewModel weatherViewModel;

    private ActivityMainBinding binding;

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
        binding = DataBindingUtil.setContentView(this,activity_main);

        currentIconImageView = binding.currentIconImageView;
        currentCityTextView = binding.cityTextView;
        currentTempTextView = binding.tempTextview;
        view = binding.mainView;

        dailyRecyclerView = binding.dailyRecyclerview;

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        } catch (Exception e) {
            longitude = -74.0060;
            latitude = 40.7128;
            showSnackbarLoc();
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
                try {
                    mCurrentWeather = currentWeather;
                    setCurrentIcon(mCurrentWeather.getIcon());
                    currentTempTextView.setText(setCurrentTemp(mCurrentWeather.getTemperature()));
                } catch (Exception e) {
                    showSnackbarData();
                    e.printStackTrace();
                }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cityName = addresses.get(0).getLocality();
        return cityName;
    }

    public void showSnackbarLoc() {
        Snackbar snackbar = Snackbar.make(view, "Cannot get the device's location!", Snackbar.LENGTH_INDEFINITE)
                .setAction("DISMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setActionTextColor(Color.WHITE);
        snackbar.show();
    }

    public void showSnackbarData() {
        Snackbar snackbar = Snackbar.make(view, "Request data from Web failed!", Snackbar.LENGTH_INDEFINITE)
                .setAction("DISMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setActionTextColor(Color.WHITE);
        snackbar.show();
    }
}

/*
1. branch11, working branch
2. unit and UI test
5. dagger2
6. rxjava
 */
