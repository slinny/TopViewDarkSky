package com.example.android.topviewdarksky.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.CurrentWeather;

import java.util.List;

import io.reactivex.Single;

//implements database calls in order to query and insert specific data
@Dao
public interface WeatherDAO{

    @Query("SELECT * FROM currentWeatherTable")
    CurrentWeather getAllCurrentData();

//    @Query("SELECT COUNT(humidity) FROM currentWeatherTable")
//    LiveData<Integer> getRowCount();

    @Insert
    Single<Long> addCurrentData(CurrentWeather weather);

//    @Insert
//    LiveData<CurrentWeather> addCurrentData(CurrentWeather weather);

    @Query("DELETE FROM currentWeatherTable")
    Single<Integer> removeAllCurrentData();

//    @Query("DELETE FROM currentWeatherTable")
//    LiveData<CurrentWeather> removeAllCurrentData();

    @Query("SELECT * FROM dailyWeatherTable")
    LiveData<List<DailyWeatherData>> getAllDailyData();

    @Insert
    void insert(List<DailyWeatherData> dailyWeatherData);

//    @Insert
//    Single<Long> addDailyData(DailyWeatherData weather);

//    @Insert
//    LiveData<List<DailyWeatherData>> addDailyData(List<DailyWeatherData> dailyWeatherDataList);

    @Query("DELETE FROM dailyWeatherTable")
    Single<Integer> removeAllDailyData();

//    @Query("DELETE FROM dailyWeatherTable")
//    LiveData<List<DailyWeatherData>> removeAllDailyData();
}
