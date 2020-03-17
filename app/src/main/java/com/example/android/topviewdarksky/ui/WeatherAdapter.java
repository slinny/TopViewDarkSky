package com.example.android.topviewdarksky.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.topviewdarksky.R;
import com.example.android.topviewdarksky.databinding.ListItemDailyBinding;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.util.TimeUtils;
import com.example.android.topviewdarksky.util.WeatherIcons;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<DailyWeatherData> dailyWeatherDataList;

    public WeatherAdapter(List<DailyWeatherData> dailyWeatherDataList) {
        this.dailyWeatherDataList = dailyWeatherDataList;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemDailyBinding listItemDailyBinding = ListItemDailyBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_daily, parent, false));
        return new WeatherViewHolder(listItemDailyBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder holder, int position) {
        DailyWeatherData dailyData = dailyWeatherDataList.get(position);
        holder.bind(dailyData);
        holder.setIcon(dailyData.getIcon());
        String day = TimeUtils.getDayOfWeek(dailyData.getTime().toString());
        holder.dateTextView.setText(day);
        holder.dailyHigh.setText(setTemp(dailyData.getTemperatureHigh()));
        holder.dailyLow.setText(setTemp(dailyData.getTemperatureLow()));
    }

    @Override
    public int getItemCount() {
        return dailyWeatherDataList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder{

        public ListItemDailyBinding listItemDailyBinding;

        TextView dateTextView, dailyHigh, dailyLow;
        ImageView dailyIconImageView;

        public WeatherViewHolder(ListItemDailyBinding listItemDailyBinding) {
            super(listItemDailyBinding.getRoot());
            this.listItemDailyBinding = listItemDailyBinding;
            dailyIconImageView = listItemDailyBinding.dailyIconImageView;
            dateTextView = listItemDailyBinding.dailyDateTextview;
            dailyHigh = listItemDailyBinding.dailyHighTextView;
            dailyLow = listItemDailyBinding.dailyLowTextView;
        }

        public void bind(Object obj) {
            listItemDailyBinding.setVariable(BR.dailyWeatherData, obj);
            listItemDailyBinding.executePendingBindings();
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
