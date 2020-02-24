package com.example.android.topviewdarksky.ui;

import android.app.Application;

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
    private WeatherRepository mWeatherRepository;

    public WeatherViewModel(Application application) {
        super(application);
      
        mWeatherRepository = new WeatherRepository(application);
        currentWeatherLiveData = mWeatherRepository.getCurrentWeather();
        dailyWeatherLiveDataList = mWeatherRepository.getDailyWeatherDataList();
    }

    public LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }
    public LiveData<List<DailyWeatherData>> getDailyWeatherLiveData() {
        return dailyWeatherLiveDataList;
    }
//
//    public void currentApiCall(Double lat, Double lon){
//        mWeatherRepository.currentAPICall(lat,lon);
//    }

//    public void insertCurrent(CurrentWeather currentWeather) {
//        mWeatherRepository.insertCurrentWeather(currentWeather);
//    }
//
//    public void deleteCurrent() {
//        mWeatherRepository.deleteCurrent();
//    }
//
//    public void dailyApiCall(Double lat, Double lon){
//        mWeatherRepository.dailyAPICall(lat,lon);
//    }
//
//    public void insertDaily(DailyWeatherData dailyWeatherData) {
//        mWeatherRepository.insertAllDailyWeather(dailyWeatherData);
//    }
//
//    public void deleteDaily() {
//        mWeatherRepository.deleteDaily();
//    }

}
