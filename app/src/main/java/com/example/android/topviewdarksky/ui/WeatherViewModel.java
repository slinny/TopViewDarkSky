package com.example.android.topviewdarksky.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.topviewdarksky.database.WeatherRepository;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private LiveData<CurrentWeather> currentWeatherLiveData;
    private LiveData<List<DailyWeatherData>> dailyWeatherLiveDataList;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        currentWeatherLiveData = WeatherRepository.getInstance(application).getCurrentWeather();
        dailyWeatherLiveDataList = WeatherRepository.getInstance(application).getDailyWeatherData();
    }

    public LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }
    public LiveData<List<DailyWeatherData>> getDailyWeatherLiveData() {
        return dailyWeatherLiveDataList;
    }

}
