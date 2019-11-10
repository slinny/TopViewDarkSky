package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @Expose
    @SerializedName("apparentTemperatureMaxTime")
    private int apparenttemperaturemaxtime;
    @Expose
    @SerializedName("apparentTemperatureMax")
    private double apparenttemperaturemax;
    @Expose
    @SerializedName("apparentTemperatureMinTime")
    private int apparenttemperaturemintime;
    @Expose
    @SerializedName("apparentTemperatureMin")
    private double apparenttemperaturemin;
    @Expose
    @SerializedName("temperatureMaxTime")
    private int temperaturemaxtime;
    @Expose
    @SerializedName("temperatureMax")
    private double temperaturemax;
    @Expose
    @SerializedName("temperatureMinTime")
    private int temperaturemintime;
    @Expose
    @SerializedName("temperatureMin")
    private double temperaturemin;
    @Expose
    @SerializedName("ozone")
    private double ozone;
    @Expose
    @SerializedName("visibility")
    private int visibility;
    @Expose
    @SerializedName("uvIndexTime")
    private int uvindextime;
    @Expose
    @SerializedName("uvIndex")
    private int uvindex;
    @Expose
    @SerializedName("cloudCover")
    private double cloudcover;
    @Expose
    @SerializedName("windBearing")
    private int windbearing;
    @Expose
    @SerializedName("windGustTime")
    private int windgusttime;
    @Expose
    @SerializedName("windGust")
    private double windgust;
    @Expose
    @SerializedName("windSpeed")
    private double windspeed;
    @Expose
    @SerializedName("pressure")
    private double pressure;
    @Expose
    @SerializedName("humidity")
    private double humidity;
    @Expose
    @SerializedName("dewPoint")
    private double dewpoint;
    @Expose
    @SerializedName("apparentTemperatureLowTime")
    private int apparenttemperaturelowtime;
    @Expose
    @SerializedName("apparentTemperatureLow")
    private double apparenttemperaturelow;
    @Expose
    @SerializedName("apparentTemperatureHighTime")
    private int apparenttemperaturehightime;
    @Expose
    @SerializedName("apparentTemperatureHigh")
    private double apparenttemperaturehigh;
    @Expose
    @SerializedName("temperatureLowTime")
    private int temperaturelowtime;
    @Expose
    @SerializedName("temperatureLow")
    private double temperaturelow;
    @Expose
    @SerializedName("temperatureHighTime")
    private int temperaturehightime;
    @Expose
    @SerializedName("temperatureHigh")
    private double temperaturehigh;
    @Expose
    @SerializedName("precipType")
    private String preciptype;
    @Expose
    @SerializedName("precipProbability")
    private double precipprobability;
    @Expose
    @SerializedName("precipIntensityMaxTime")
    private int precipintensitymaxtime;
    @Expose
    @SerializedName("precipIntensityMax")
    private double precipintensitymax;
    @Expose
    @SerializedName("precipIntensity")
    private double precipintensity;
    @Expose
    @SerializedName("moonPhase")
    private double moonphase;
    @Expose
    @SerializedName("sunsetTime")
    private int sunsettime;
    @Expose
    @SerializedName("sunriseTime")
    private int sunrisetime;
    @Expose
    @SerializedName("icon")
    private String icon;
    @Expose
    @SerializedName("summary")
    private String summary;
    @Expose
    @SerializedName("time")
    private int time;

    public int getApparenttemperaturemaxtime() {
        return apparenttemperaturemaxtime;
    }

    public void setApparenttemperaturemaxtime(int apparenttemperaturemaxtime) {
        this.apparenttemperaturemaxtime = apparenttemperaturemaxtime;
    }

    public double getApparenttemperaturemax() {
        return apparenttemperaturemax;
    }

    public void setApparenttemperaturemax(double apparenttemperaturemax) {
        this.apparenttemperaturemax = apparenttemperaturemax;
    }

    public int getApparenttemperaturemintime() {
        return apparenttemperaturemintime;
    }

    public void setApparenttemperaturemintime(int apparenttemperaturemintime) {
        this.apparenttemperaturemintime = apparenttemperaturemintime;
    }

    public double getApparenttemperaturemin() {
        return apparenttemperaturemin;
    }

    public void setApparenttemperaturemin(double apparenttemperaturemin) {
        this.apparenttemperaturemin = apparenttemperaturemin;
    }

    public int getTemperaturemaxtime() {
        return temperaturemaxtime;
    }

    public void setTemperaturemaxtime(int temperaturemaxtime) {
        this.temperaturemaxtime = temperaturemaxtime;
    }

    public double getTemperaturemax() {
        return temperaturemax;
    }

    public void setTemperaturemax(double temperaturemax) {
        this.temperaturemax = temperaturemax;
    }

    public int getTemperaturemintime() {
        return temperaturemintime;
    }

    public void setTemperaturemintime(int temperaturemintime) {
        this.temperaturemintime = temperaturemintime;
    }

    public double getTemperaturemin() {
        return temperaturemin;
    }

    public void setTemperaturemin(double temperaturemin) {
        this.temperaturemin = temperaturemin;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getUvindextime() {
        return uvindextime;
    }

    public void setUvindextime(int uvindextime) {
        this.uvindextime = uvindextime;
    }

    public int getUvindex() {
        return uvindex;
    }

    public void setUvindex(int uvindex) {
        this.uvindex = uvindex;
    }

    public double getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(double cloudcover) {
        this.cloudcover = cloudcover;
    }

    public int getWindbearing() {
        return windbearing;
    }

    public void setWindbearing(int windbearing) {
        this.windbearing = windbearing;
    }

    public int getWindgusttime() {
        return windgusttime;
    }

    public void setWindgusttime(int windgusttime) {
        this.windgusttime = windgusttime;
    }

    public double getWindgust() {
        return windgust;
    }

    public void setWindgust(double windgust) {
        this.windgust = windgust;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(double dewpoint) {
        this.dewpoint = dewpoint;
    }

    public int getApparenttemperaturelowtime() {
        return apparenttemperaturelowtime;
    }

    public void setApparenttemperaturelowtime(int apparenttemperaturelowtime) {
        this.apparenttemperaturelowtime = apparenttemperaturelowtime;
    }

    public double getApparenttemperaturelow() {
        return apparenttemperaturelow;
    }

    public void setApparenttemperaturelow(double apparenttemperaturelow) {
        this.apparenttemperaturelow = apparenttemperaturelow;
    }

    public int getApparenttemperaturehightime() {
        return apparenttemperaturehightime;
    }

    public void setApparenttemperaturehightime(int apparenttemperaturehightime) {
        this.apparenttemperaturehightime = apparenttemperaturehightime;
    }

    public double getApparenttemperaturehigh() {
        return apparenttemperaturehigh;
    }

    public void setApparenttemperaturehigh(double apparenttemperaturehigh) {
        this.apparenttemperaturehigh = apparenttemperaturehigh;
    }

    public int getTemperaturelowtime() {
        return temperaturelowtime;
    }

    public void setTemperaturelowtime(int temperaturelowtime) {
        this.temperaturelowtime = temperaturelowtime;
    }

    public double getTemperaturelow() {
        return temperaturelow;
    }

    public void setTemperaturelow(double temperaturelow) {
        this.temperaturelow = temperaturelow;
    }

    public int getTemperaturehightime() {
        return temperaturehightime;
    }

    public void setTemperaturehightime(int temperaturehightime) {
        this.temperaturehightime = temperaturehightime;
    }

    public double getTemperaturehigh() {
        return temperaturehigh;
    }

    public void setTemperaturehigh(double temperaturehigh) {
        this.temperaturehigh = temperaturehigh;
    }

    public String getPreciptype() {
        return preciptype;
    }

    public void setPreciptype(String preciptype) {
        this.preciptype = preciptype;
    }

    public double getPrecipprobability() {
        return precipprobability;
    }

    public void setPrecipprobability(double precipprobability) {
        this.precipprobability = precipprobability;
    }

    public int getPrecipintensitymaxtime() {
        return precipintensitymaxtime;
    }

    public void setPrecipintensitymaxtime(int precipintensitymaxtime) {
        this.precipintensitymaxtime = precipintensitymaxtime;
    }

    public double getPrecipintensitymax() {
        return precipintensitymax;
    }

    public void setPrecipintensitymax(double precipintensitymax) {
        this.precipintensitymax = precipintensitymax;
    }

    public double getPrecipintensity() {
        return precipintensity;
    }

    public void setPrecipintensity(double precipintensity) {
        this.precipintensity = precipintensity;
    }

    public double getMoonphase() {
        return moonphase;
    }

    public void setMoonphase(double moonphase) {
        this.moonphase = moonphase;
    }

    public int getSunsettime() {
        return sunsettime;
    }

    public void setSunsettime(int sunsettime) {
        this.sunsettime = sunsettime;
    }

    public int getSunrisetime() {
        return sunrisetime;
    }

    public void setSunrisetime(int sunrisetime) {
        this.sunrisetime = sunrisetime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
