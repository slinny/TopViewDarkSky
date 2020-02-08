package com.example.android.topviewdarksky.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.topviewdarksky.database.Repository;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;

import java.util.List;

import static com.example.android.topviewdarksky.MainActivity.latitude;
import static com.example.android.topviewdarksky.MainActivity.longitude;

public class WeatherViewModel extends AndroidViewModel {

    private LiveData<CurrentWeather> currentWeatherLiveData;
    private LiveData<List<DailyWeatherData>> dailyWeatherLiveDataList;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        currentWeatherLiveData = Repository.getInstance().currentAPICall(latitude,longitude);
        dailyWeatherLiveDataList = Repository.getInstance().dailyAPICall(latitude,longitude);
    }

    public LiveData<CurrentWeather> getCurrentWeatherLiveData() {
//        Log.d("VMCT", currentWeatherLiveData.getValue().getTemperature().toString());
        return currentWeatherLiveData;
    }
    public LiveData<List<DailyWeatherData>> getDailyWeatherLiveDataListWeatherLiveData() {
//        Log.d("VMDT", dailyWeatherLiveDataList.getValue().get(0).getTemperatureHigh().toString());
        return dailyWeatherLiveDataList;
    }
}
