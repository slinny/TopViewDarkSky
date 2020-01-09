package com.example.android.topviewdarksky.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {

    @SerializedName("data")
    List<Data> data = null;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
