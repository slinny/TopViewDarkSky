package com.example.android.topviewdarksky.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.topviewdarksky.database.WeatherRepository;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;

import java.util.List;

import static com.example.android.topviewdarksky.MainActivity.latitude;
import static com.example.android.topviewdarksky.MainActivity.longitude;

public class WeatherViewModel extends AndroidViewModel {

    private LiveData<CurrentWeather> currentWeatherLiveData;
    private LiveData<List<DailyWeatherData>> dailyWeatherLiveDataList;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        currentWeatherLiveData = WeatherRepository.getInstance().getCurrentWeather(latitude,longitude);
        dailyWeatherLiveDataList = WeatherRepository.getInstance().getDailyWeather(latitude,longitude);
    }

    public LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }
    public LiveData<List<DailyWeatherData>> getDailyWeatherLiveDataListWeatherLiveData() {
        return dailyWeatherLiveDataList;
    }
}
