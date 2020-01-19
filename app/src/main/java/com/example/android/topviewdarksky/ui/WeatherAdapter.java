package com.example.android.topviewdarksky.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.topviewdarksky.R;
import com.example.android.topviewdarksky.models.DailyWeatherData;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    Context context;
    ArrayList<DailyWeatherData> dailyWeatherDataArrayList;

    public WeatherAdapter(Context context, ArrayList<DailyWeatherData> dailyWeatherDataArrayList) {
        this.context = context;
        this.dailyWeatherDataArrayList = dailyWeatherDataArrayList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_daily, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dailyWeatherDataArrayList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder{

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
