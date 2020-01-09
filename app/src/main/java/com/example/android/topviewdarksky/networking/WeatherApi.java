package com.example.android.topviewdarksky.networking;

import android.database.Observable;

import com.example.android.topviewdarksky.models.Weather;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface WeatherApi {

    @GET("{lat},{lon}")
    Observable<Response<Weather>> getWeather(
            @Path("lat") double lat,
            @Path("lon") double lon
    );
}