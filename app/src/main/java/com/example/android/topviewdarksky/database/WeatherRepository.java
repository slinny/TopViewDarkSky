package com.example.android.topviewdarksky.database;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WeatherDAO weatherDAO;
    private ApiService apiService;
    private final Executor executor;

    private static WeatherRepository repository;

    public WeatherRepository(){
        apiService = RetrofitCall.getAllWeather();
        executor = new Executor() {
            @Override
            public void execute(Runnable runnable) {

            }
        };
    }

    public synchronized static WeatherRepository getInstance() {
        if (repository == null) {
            if (repository == null) {
                repository = new WeatherRepository();
            }
        }
        return repository;
    }

    public MutableLiveData<CurrentWeather> getCurrentWeather(Double lat, Double lon) {
        final MutableLiveData<CurrentWeather> currentWeatherData = new MutableLiveData<>();
            apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call,
                                       Response<Weather> response) {
                    if (response.isSuccessful()) {
                        CurrentWeather currentWeather = response.body().getCurrentWeather();
                        currentWeatherData.setValue(currentWeather);
                        executor.execute(() -> {
                            weatherDAO.insertCurrentData(currentWeather);
                        });
                    }
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                    executor.execute(() -> {
                        currentWeatherData.setValue(weatherDAO.getAllCurrentData().getValue());
                    });
                }
            });

        return currentWeatherData;
    }

    public MutableLiveData<List<DailyWeatherData>> getDailyWeather(Double lat, Double lon){
        final MutableLiveData<List<DailyWeatherData>> dailyWeatherData = new MutableLiveData<>();
        apiService.getWeather(lat,lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call,
                                   Response<Weather> response) {
                if (response.isSuccessful()){
                    List<DailyWeatherData> dailyWeatherDataList = response.body().getDailyWeather().getData();
                    dailyWeatherData.setValue(response.body().getDailyWeather().getData());
                    executor.execute(() -> {
                        for (int i = 0; i < dailyWeatherDataList.size(); i++) {
                            weatherDAO.insertDailyData(dailyWeatherDataList.get(i));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                executor.execute(() -> {
                    dailyWeatherData.setValue(weatherDAO.getAllDailyData().getValue());
                });
            }
        });
        return dailyWeatherData;
    }
}