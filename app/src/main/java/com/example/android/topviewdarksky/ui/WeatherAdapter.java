package com.example.android.topviewdarksky.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.topviewdarksky.R;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.util.TimeUtils;
import com.example.android.topviewdarksky.util.WeatherIcons;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    ArrayList<DailyWeatherData> dailyWeatherDataArrayList;

    public WeatherAdapter(ArrayList<DailyWeatherData> dailyWeatherDataArrayList) {
        this.dailyWeatherDataArrayList = dailyWeatherDataArrayList;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_daily, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder holder, int position) {
        DailyWeatherData dailyData = dailyWeatherDataArrayList.get(position);

        holder.setIcon(dailyData.getIcon());

        String day = TimeUtils.getDayOfWeek(dailyData.getTime().toString());
        holder.dateTextView.setText(day);
        holder.dailyHigh.setText(setTemp(dailyData.getTemperatureHigh()));
        holder.dailyLow.setText(setTemp(dailyData.getTemperatureLow()));

    }

    @Override
    public int getItemCount() {
        return dailyWeatherDataArrayList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder{

        TextView dateTextView, dailyHigh, dailyLow;
        ImageView dailyIconImageView;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyIconImageView =itemView.findViewById(R.id.daily_icon_imageView);
            dateTextView = itemView.findViewById(R.id.daily_date_textview);
            dailyHigh = itemView.findViewById(R.id.daily_high_textView);
            dailyLow = itemView.findViewById(R.id.daily_low_textView);

        }

        public void setWeekday(String weekday) {
            this.dateTextView.setText(weekday);
        }
        public void setIcon(String resource) {
            Integer imageRsc = WeatherIcons.getIconResource(resource);
            this.dailyIconImageView.setImageResource(Integer.valueOf(imageRsc));
        }
    }

    public String setTemp(String temperature) {
        float tempInt = Float.valueOf(temperature);
        int tempR = Math.round(tempInt);
        String tempS = String.valueOf(tempR);
        return tempS + " \u2109";
    }
}
