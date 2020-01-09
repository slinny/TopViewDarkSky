package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {

    @SerializedName("timezone")
    private String timezone = "";

    @SerializedName("daily")
    private Daily dailyWeather = null;

    @SerializedName("currently")
    private Currently currentWeather = null;

    public String getTimezone(){
        return timezone;
    }

    public void setTimezone(String timezone){
        this.timezone = timezone;
    }

    public Daily getDailyWeather(){
        return dailyWeather;
    }


    public void setDailyWeather(Daily dailyWeather){
        this.dailyWeather = dailyWeather;
    }

    public Currently getCurrentWeather(){
        return currentWeather;
    }

    public void setCurrentWeather(Currently currentWeather){
        this.currentWeather = currentWeather;
    }

}
