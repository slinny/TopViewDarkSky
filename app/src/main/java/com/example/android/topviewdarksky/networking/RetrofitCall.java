package com.example.android.topviewdarksky.networking;

public class RetrofitCall {

    public static ApiService getAllWeather() {
        return RetrofitClient.getRetrofitClient(ApiService.URL).create(ApiService.class);
    }
}
