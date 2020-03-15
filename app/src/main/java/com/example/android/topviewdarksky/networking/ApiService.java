package com.example.android.topviewdarksky.networking;

import com.example.android.topviewdarksky.BuildConfig;
import com.example.android.topviewdarksky.models.Weather;

import org.intellij.lang.annotations.Flow;

import java.io.File;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {

    public static final String BASE_URL = "https://api.darksky.net/forecast";
    public static final String SECRET_KEY = BuildConfig.ApiKey;

    public static final String URL = BASE_URL + File.separator + SECRET_KEY + File.separator;

//    @GET("{lat},{lon}")
//    Call<Weather> getWeather(
//            @Path("lat") double lat,
//            @Path("lon") double lon
//    );

    @GET("{lat},{lon}")
    Flowable<Weather> getWeather(
            @Path("lat") double lat,
            @Path("lon") double lon
    );
}