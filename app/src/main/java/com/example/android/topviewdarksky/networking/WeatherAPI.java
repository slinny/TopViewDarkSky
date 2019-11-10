package com.example.android.topviewdarksky.networking;

import com.example.android.topviewdarksky.models.WeatherModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface WeatherAPI {

    @GET("{key}/{latitude},{longitude},{time}")
    Flowable<WeatherModel> getFutureWeather(
            @Path("key") String key,
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude,
            @Path("time") String time
    );

    @GET("{key}/{latitude},{longitude}")
    Flowable<WeatherModel> getCurrentWeather(
            @Path("key") String key,
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude
    );
}