package com.example.android.topviewdarksky.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.CurrentWeather;

import kotlin.jvm.Synchronized;

@Database(entities ={CurrentWeather.class, DailyWeatherData.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDAO weatherDAO();
    private static WeatherDatabase instance = null;

    @Synchronized
    public static WeatherDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,
                    WeatherDatabase.class, "weatherDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
