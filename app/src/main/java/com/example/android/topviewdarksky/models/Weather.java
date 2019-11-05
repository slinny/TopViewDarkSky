package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {

    @SerializedName("timezone")
    private String timezone = "";

    @SerializedName("daily")
    private DailyWeather dailyWeather = null;

    @SerializedName("currently")
    private CurrentWeather currentWeather = null;

    public String getTimezone(){
        return timezone;
    }

    public void setTimezone(String timezone){
        this.timezone = timezone;
    }

    public DailyWeather getDailyWeather(){
        return dailyWeather;
    }


    public void setDailyWeather(DailyWeather dailyWeather){
        this.dailyWeather = dailyWeather;
    }

    public CurrentWeather getCurrentWeather(){
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather){
        this.currentWeather = currentWeather;
    }

}
