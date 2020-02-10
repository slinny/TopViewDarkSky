package com.example.android.topviewdarksky.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> addCurrentData(CurrentWeather weather);

    @Query("DELETE FROM currentWeatherTable")
    Single<Integer> removeAllCurrentData();

    @Query("SELECT * FROM dailyWeatherTable")
    LiveData<List<DailyWeatherData>> getAllDailyData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> addDailyData(DailyWeatherData weather);

    @Query("DELETE FROM dailyWeatherTable")
    Single<Integer> removeAllDailyData();
}
