package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {
    @Expose
    @SerializedName("data")
    private List<Data> data;
    @Expose
    @SerializedName("icon")
    private String icon;
    @Expose
    @SerializedName("summary")
    private String summary;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
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
}
