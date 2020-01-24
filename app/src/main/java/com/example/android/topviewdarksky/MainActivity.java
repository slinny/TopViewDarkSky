package com.example.android.topviewdarksky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<DailyWeatherData> dailyWeatherDataList = new ArrayList<>();
    private WeatherAdapter weatherAdapter;
    private ApiService apiService;
    private LinearLayoutManager linearLayoutManager;
    public static Context context;

    GPSTracker gps;
    double latitude;
    double longitude;

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

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.init();

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

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        dailyRecyclerView.setLayoutManager(linearLayoutManager);

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

//    private void getLocation() {
//        gps = new GPSTracker(context);
//
//        if (gps.canGetLocation()) {
//
//            latitude = gps.getLatitude();
//            longitude = gps.getLongitude();
//            Log.d("latitude", String.valueOf(latitude));
//            Log.d("longtitude", String.valueOf(longitude));
//        } else {
////            gps.showSettingsAlert();
//            Log.d("GPSTracker", "getLocation failed");
//        }
//    }
}

/*
1. databinding
2. database
3. repository and viewmodel
4. livedata
5. dagger2
6. rxjava
 */
