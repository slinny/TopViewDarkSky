package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currently {
    @Expose
    @SerializedName("ozone")
    private double ozone;
    @Expose
    @SerializedName("visibility")
    private double visibility;
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
    @SerializedName("apparentTemperature")
    private double apparenttemperature;
    @Expose
    @SerializedName("temperature")
    private double temperature;
    @Expose
    @SerializedName("precipType")
    private String preciptype;
    @Expose
    @SerializedName("precipProbability")
    private double precipprobability;
    @Expose
    @SerializedName("precipIntensityError")
    private double precipintensityerror;
    @Expose
    @SerializedName("precipIntensity")
    private double precipintensity;
    @Expose
    @SerializedName("nearestStormDistance")
    private int neareststormdistance;
    @Expose
    @SerializedName("icon")
    private String icon;
    @Expose
    @SerializedName("summary")
    private String summary;
    @Expose
    @SerializedName("time")
    private int time;

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
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

    public double getApparenttemperature() {
        return apparenttemperature;
    }

    public void setApparenttemperature(double apparenttemperature) {
        this.apparenttemperature = apparenttemperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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

    public double getPrecipintensityerror() {
        return precipintensityerror;
    }

    public void setPrecipintensityerror(double precipintensityerror) {
        this.precipintensityerror = precipintensityerror;
    }

    public double getPrecipintensity() {
        return precipintensity;
    }

    public void setPrecipintensity(double precipintensity) {
        this.precipintensity = precipintensity;
    }

    public int getNeareststormdistance() {
        return neareststormdistance;
    }

    public void setNeareststormdistance(int neareststormdistance) {
        this.neareststormdistance = neareststormdistance;
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
