package com.example.android.topviewdarksky.networking;

import com.example.android.topviewdarksky.BuildConfig;

import java.io.File;

import retrofit2.Response;

public class RetrofitCall {
    private static final String BASE_URL = "https://api.darksky.net/forecast";
    private static final String SECRET_KEY = BuildConfig.ApiKey;

    private static final String URL = BASE_URL + File.separator + SECRET_KEY + File.separator;

    public static WeatherService getAllWeather() {
        return RetrofitClient.getRetrofitClient(URL).create(WeatherService.class);
    }
}
