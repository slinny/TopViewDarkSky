package com.example.android.topviewdarksky.ui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
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

public class WeatherViewModel extends AndroidViewModel {

    private LiveData<CurrentWeather> currentWeatherLiveData;
    private LiveData<List<DailyWeatherData>> dailyWeatherLiveDataList;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        currentWeatherLiveData = Repository.getInstance().getCurrentWeather(latitude,longitude);
        dailyWeatherLiveDataList = Repository.getInstance().getDailyWeather(latitude,longitude);
    }

    public LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }
    public LiveData<List<DailyWeatherData>> getDailyWeatherLiveDataListWeatherLiveData() {
        return dailyWeatherLiveDataList;
    }
}
