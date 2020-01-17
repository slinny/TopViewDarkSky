package com.example.android.topviewdarksky.ui;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.topviewdarksky.MainActivity;
import com.example.android.topviewdarksky.database.Repository;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<CurrentWeather> currentWeatherMutableLiveData;
    private MutableLiveData<List<DailyWeatherData>> dailyWeatherDataList;
    private Repository repository;
    private Context context;

    public void init(){
        if(currentWeatherMutableLiveData != null){
            return;
        }
        repository = Repository.getInstance();
        currentWeatherMutableLiveData = (MutableLiveData<CurrentWeather>) repository.addCurrentWeatherData(context);

        if(dailyWeatherDataList != null){
            return;
        }
        repository = Repository.getInstance();
        dailyWeatherDataList = repository.addDailyWeatherData(context);
    }

    public LiveData<CurrentWeather> getCurrentWeather(){return currentWeatherMutableLiveData;}
    public LiveData<List<DailyWeatherData>> getAllDailyWeather(){return dailyWeatherDataList;}
}
