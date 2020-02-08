package com.example.android.topviewdarksky.database;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private WeatherDAO weatherDAO;
    private ApiService apiService;
    private static Repository repository;

    public Repository() {
        apiService = RetrofitCall.getAllWeather();
    }

    public synchronized static Repository getInstance() {
        if (repository == null) {
            if (repository == null) {
                repository = new Repository();
            }
        }
        return repository;
    }

    public MutableLiveData<CurrentWeather> currentAPICall(Double lat, Double lon) {
        final MutableLiveData<CurrentWeather> currentWeatherData = new MutableLiveData<>();
        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call,
                                   Response<Weather> response) {
                if (response.isSuccessful()) {
                    currentWeatherData.setValue(response.body().getCurrentWeather());
//                    if (weatherDAO.getAllCurrentData().getTemperature().toString() != null) {
//                        weatherDAO.removeAllCurrentData();
//                    }
//                    weatherDAO.addCurrentData(response.body().currentAPICall());
//                    Log.d("repCT", response.body().currentAPICall().getTemperature().toString());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
//                if (weatherDAO.getAllCurrentData() != null) {
//                    currentWeatherData.setValue(weatherDAO.getAllCurrentData());
//                }
            }
        });
        return currentWeatherData;
    }

    public MutableLiveData<List<DailyWeatherData>> dailyAPICall(Double lat, Double lon) {
        final MutableLiveData<List<DailyWeatherData>> dailyWeatherData = new MutableLiveData<>();
        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call,
                                   Response<Weather> response) {
                if (response.isSuccessful()) {
                    dailyWeatherData.setValue(response.body().getDailyWeather().getData());
//                    if (weatherDAO.getAllDailyData().getValue().get(0).getTemperatureHigh().toString() != null) {
//                        weatherDAO.removeAllDailyData();
//                    }
//                    weatherDAO.insert(response.body().dailyAPICall().getData());
//                    Log.d("repDT", response.body().dailyAPICall().getData().get(0).getTemperatureHigh().toString());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
//                if (weatherDAO.getAllCurrentData() != null) {
//                    dailyWeatherData.setValue((List<DailyWeatherData>) weatherDAO.getAllCurrentData());
//                }
            }
        });
        return dailyWeatherData;
    }

//    public
}