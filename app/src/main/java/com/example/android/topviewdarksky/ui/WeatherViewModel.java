package com.example.android.topviewdarksky.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.topviewdarksky.database.Repository;
import com.example.android.topviewdarksky.models.Currently;
import com.example.android.topviewdarksky.models.Data;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.util.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class WeatherViewModel extends ViewModel {

    private Repository repository;
    private LiveData<Currently> allCurrentData;
    private LiveData<List<Data>> allDailyData;

    @Inject
    public WeatherViewModel(Repository repository) {
        this.repository = repository;
        allCurrentData = this.repository.getAllCurrentData();
        allDailyData = this.repository.getAllDailyData();
    }

    public LiveData<Weather> currentAPICall(String key, Double latitude, Double longitude){
        return repository.currentAPICall(key, latitude,longitude);
    }

    public LiveData<Weather> futureAPICall(String key, Double latitude, Double longitude, String time){
        return repository.futureAPICall(key,latitude,longitude,time);
    }

    public Flowable<Resource<Integer>> addCurrentWeather(Currently currentWeather){
        return repository.insertCurrentData(currentWeather);
    }

    public Flowable<Resource<Integer>> deleteCurrentWeather(){
        return repository.deleteCurrentData();

    }

    public LiveData<Integer> getcount(){
        return repository.getcount();
    }

    public LiveData<Currently> getAllCurrentData(){
        return allCurrentData;
    }


    public Flowable<Resource<Integer>> addDailyWeather(Data dailyWeatherData){
        return  repository.addDailyData(dailyWeatherData);
    }

    public Flowable<Resource<Integer>> deleteDailyWeather(){
        return repository.deleteDailyData();
    }


    public LiveData<List<Data>> getAllDailyData(){
        return allDailyData;
    }

}