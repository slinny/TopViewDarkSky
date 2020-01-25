package com.example.android.topviewdarksky.ui;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.topviewdarksky.MainActivity;
import com.example.android.topviewdarksky.database.Repository;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.util.GPSTracker;

import java.util.List;

import static com.example.android.topviewdarksky.MainActivity.latitude;
import static com.example.android.topviewdarksky.MainActivity.longitude;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<Weather> weatherMutableLiveData;
    private Repository repository;

    public void init() {
        if (weatherMutableLiveData != null) {
            return;
        }

        repository = Repository.getInstance();
        weatherMutableLiveData = repository.getWeather(latitude, longitude);
    }

    public LiveData<Weather> getWeatherepository() {
        return weatherMutableLiveData;
    }
}
