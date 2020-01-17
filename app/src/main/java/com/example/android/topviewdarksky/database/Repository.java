package com.example.android.topviewdarksky.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private int delay = 0;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    private double lat = 42.3601;
    private double lon = -71.0589;

    public static int counter = 0;
    public static Boolean noWifi = true;
    private WeatherDAO weatherDAO;
    private ApiService apiService;
    private static Repository repository;

    private Repository() {
        //TODO this apiService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public synchronized static Repository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (repository == null) {
            if (repository == null) {
                repository = new Repository();
            }
        }
        return repository;
    }

    public LiveData<CurrentWeather> addCurrentWeatherData(final Context context) {
        final MutableLiveData<CurrentWeather> currentData = new MutableLiveData<>();

        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather weather = response.body();
                CurrentWeather currentWeather = null;

                if (weather != null) {
                    currentWeather = weather.getCurrentWeather();
                }
                weatherDAO = WeatherDatabase
                        .getInstance(context)
                        .weatherDAO();

                weatherDAO.removeAllCurrentData();
                weatherDAO.addCurrentData(currentWeather);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // TODO better error handling in part #2 ...

                currentData.setValue(WeatherDatabase
                        .getInstance(context)
                        .weatherDAO()
                        .getAllCurrentData());

            }
        });

        return currentData;
    }

    public MutableLiveData<List<DailyWeatherData>> addDailyWeatherData(final Context context) {
        final MutableLiveData<List<DailyWeatherData>> dailyWeatherDataList = new MutableLiveData<>();

        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather weather = response.body();

                if (weather != null) {
                    DailyWeatherData dailyWeatherData = (DailyWeatherData) weather.getDailyWeather().getData();
                }

                weatherDAO = WeatherDatabase
                        .getInstance(context)
                        .weatherDAO();

                weatherDAO.removeAllDailyData();

                for (int i = 0; i<response.body().getDailyWeather().getData().size(); i++) {
                    DailyWeatherData dailyWeatherData = new DailyWeatherData(response.body().getDailyWeather().getData().get(i));

                    weatherDAO.addDailyData(dailyWeatherData);
                }

                dailyWeatherDataList.setValue((List<DailyWeatherData>) dailyWeatherDataList);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // TODO better error handling in part #2 ...

                dailyWeatherDataList.setValue((List<DailyWeatherData>) WeatherDatabase
                        .getInstance(context)
                        .weatherDAO()
                        .getAllDailyData());

            }
        });

        return dailyWeatherDataList;
    }
}
