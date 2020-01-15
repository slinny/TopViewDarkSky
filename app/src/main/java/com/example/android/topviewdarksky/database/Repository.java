package com.example.android.topviewdarksky.database;

import java.util.concurrent.TimeUnit;

import com.example.android.topviewdarksky.BuildConfig;
import com.example.android.topviewdarksky.networking.WeatherService;

import retrofit2.Retrofit;

public class Repository {

    private int delay = 0;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public static int counter = 0;
    public static Boolean noWifi = true;
    private WeatherDAO weatherDAO;
    private WeatherService weatherService;






}
