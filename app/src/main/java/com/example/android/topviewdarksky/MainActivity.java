package com.example.android.topviewdarksky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;
import com.example.android.topviewdarksky.ui.WeatherAdapter;
import com.example.android.topviewdarksky.ui.WeatherViewModel;
import com.example.android.topviewdarksky.util.GPSTracker;
import com.example.android.topviewdarksky.util.WeatherIcons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<DailyWeatherData> dailyWeatherDataArrayList = new ArrayList<>();
    private CurrentWeather currentWeather;
    private WeatherAdapter weatherAdapter;
    private LinearLayoutManager linearLayoutManager;
    Context context;

    GPSTracker gps;
    public static double latitude;
    public static double longitude;

    WeatherViewModel weatherViewModel;

    ImageView currentIconImageView;
    TextView currentCityTextView;
    TextView currentTempTextView;
    RecyclerView dailyRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentIconImageView = findViewById(R.id.current_icon_imageView);
        currentCityTextView = findViewById(R.id.city_textView);
        currentTempTextView = findViewById(R.id.temp_textview);

        dailyRecyclerView = findViewById(R.id.daily_recyclerview);

        getLocation();
        Log.d("mainlat", String.valueOf(latitude));

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.init();
        weatherViewModel.getWeatherepository().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                currentWeather = weather.getCurrentWeather();
                List<DailyWeatherData> dailyWeatherDataList = weather.getDailyWeather().getData();
                dailyWeatherDataArrayList.addAll(dailyWeatherDataList);
                weatherAdapter.notifyDataSetChanged();
            }
        });

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        dailyRecyclerView.setLayoutManager(linearLayoutManager);
        setCurrentIcon(currentWeather.getIcon());
        currentTempTextView.setText(setCurrentTemp(currentWeather.getTemperature()));
        currentCityTextView.setText(getCurrentCityName(latitude,longitude));

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
        Address address = null;
        try {
            address = (Address) geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cityName = address.getAddressLine(0);
        return cityName;
    }

    private void getLocation() {
        gps = new GPSTracker(context);

        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Log.d("latitude", String.valueOf(latitude));
            Log.d("longtitude", String.valueOf(longitude));
        } else {
            Log.d("GPSTracker", "getLocation failed");
        }
    }

}

/*
1. databinding
2. database
3. repository and viewmodel
4. livedata
5. dagger2
6. rxjava
 */

//        weatherViewModel.getCurrentWeather().observe(this, new Observer<CurrentWeather>() {
//            @Override
//            public void onChanged(CurrentWeather currentWeather) {
//                String currentTemp = setCurrentTemp(currentWeather.getTemperature());
//                currentTempTextView.setText(currentTemp);
//                setCurrentIcon(currentWeather.getIcon());
//            }
//        });

//        weatherViewModel.getAllDailyWeather().observe(this, new Observer<List<DailyWeatherData>>() {
//            @Override
//            public void onChanged(List<DailyWeatherData> dailyWeatherDataList) {
//                List<DailyWeatherData> dailyWeatherDataList1 = dailyWeatherDataList;
//                dailyWeatherDataList.add((DailyWeatherData) dailyWeatherDataList1);
//                weatherAdapter.notifyDataSetChanged();
//            }
//        });

//        getLocation();
//
//        apiService = RetrofitCall.getAllWeather();
//
//        apiService.getWeather(latitude, longitude).enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                dailyWeatherDataList = response.body().getDailyWeather().getData();
//                weatherAdapter = new WeatherAdapter((ArrayList<DailyWeatherData>) dailyWeatherDataList);
//                dailyRecyclerView.setAdapter(weatherAdapter);
//
//                String currentTemp = setCurrentTemp(response.body().getCurrentWeather().getTemperature());
//                currentTempTextView.setText(currentTemp);
//                setCurrentIcon(response.body().getCurrentWeather().getIcon());
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });