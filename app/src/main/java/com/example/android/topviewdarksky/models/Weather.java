package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class Weather {

    @Expose
    @SerializedName("daily")
    private Daily daily;
    @Expose
    @SerializedName("currently")
    private Currently currently;
    @Expose
    @SerializedName("timezone")
    private String timezone;
    @Expose
    @SerializedName("longitude")
    private double longitude;
    @Expose
    @SerializedName("latitude")
    private double latitude;

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
