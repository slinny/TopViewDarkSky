package com.example.android.topviewdarksky.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.topviewdarksky.models.Currently;
import com.example.android.topviewdarksky.models.Data;

import java.util.List;

import io.reactivex.Single;

//implements database calls in order to query and insert specific data
@Dao
public interface WeatherDAO{

    @Query("SELECT * FROM currentWeatherTable")
    LiveData<Currently> getAllCurrentData();

    @Query("SELECT COUNT(humidity) FROM currentWeatherTable")
    LiveData<Integer> getRowCount();

    @Insert
    Single<Long> addCurrentData(Currently weather);

    @Query("DELETE FROM currentWeatherTable")
    Single<Integer> removeAllCurrentData();

    @Query("SELECT * FROM dailyWeatherTable")
    LiveData<List<Data>> getAllDailyData();

    @Insert
    Single<Long> addDailyData(Data weather);

    @Query("DELETE FROM dailyWeatherTable")
    Single<Integer> removeAllDailyData();

}
