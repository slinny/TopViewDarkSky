package com.example.android.topviewdarksky.networking;

import com.example.android.topviewdarksky.BuildConfig;

import java.io.File;

public class RetrofitCall {

    public static ApiService getAllWeather() {
        return RetrofitClient.getRetrofitClient(ApiService.URL).create(ApiService.class);
    }
}
