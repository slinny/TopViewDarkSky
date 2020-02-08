package com.example.android.topviewdarksky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.ui.WeatherAdapter;
import com.example.android.topviewdarksky.ui.WeatherViewModel;
import com.example.android.topviewdarksky.util.MyLocationListener;
import com.example.android.topviewdarksky.util.WeatherIcons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.android.topviewdarksky.util.Constants.CITY_NAME_KEY;
import static com.example.android.topviewdarksky.util.Constants.DEFAULT_CITY_NAME;
import static com.example.android.topviewdarksky.util.Constants.SHARED_PREFS;

public class MainActivity extends AppCompatActivity  {

    public SharedPreferences sharedPreferences;
    private List<DailyWeatherData> dailyWeatherDataArrayList = new ArrayList<>();
    private CurrentWeather currentWeather;
    private WeatherAdapter weatherAdapter;
    private LinearLayoutManager linearLayoutManager;
    Context context;
    String currentCityName;

    public static Double latitude;
    public static Double longitude;
    Location location = null;

    WeatherViewModel weatherViewModel;

    ImageView currentIconImageView;
    TextView currentCityTextView;
    TextView currentTempTextView;
    RecyclerView dailyRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getlocation();

//        latitude = location.getLatitude();
//        longitude = location.getLongitude();

        latitude = 40.7128;
        longitude = -74.0060;

        sharedPreferences = getApplicationContext().getSharedPreferences(CITY_NAME_KEY,Context.MODE_PRIVATE);

        currentIconImageView = findViewById(R.id.current_icon_imageView);
        currentCityTextView = findViewById(R.id.city_textView);
        currentTempTextView = findViewById(R.id.temp_textview);

        dailyRecyclerView = findViewById(R.id.daily_recyclerview);

        if(latitude != null && longitude != null) {
            currentCityName = getCurrentCityName(latitude, longitude);
            Log.d("mainCTN", currentCityName);
            currentCityTextView.setText(currentCityName);
            sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString(CITY_NAME_KEY,currentCityName).apply();
        }else if(sharedPreferences.getString(CITY_NAME_KEY,null) != null){
            currentCityName = sharedPreferences.getString(CITY_NAME_KEY,null);
            currentCityTextView.setText(currentCityName);
        }else{
            currentCityTextView.setText(DEFAULT_CITY_NAME);
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

    private void getlocation(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
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
}

/*
1. databinding
5. dagger2
6. rxjava
 */