package com.example.android.topviewdarksky.database;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.List;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private WeatherDAO weatherDAO;
    private ApiService apiService;

    private static Repository repository;

    public Repository(Application application) {
        WeatherDatabase weatherDatabase = WeatherDatabase.getInstance(application);
        apiService = RetrofitCall.getAllWeather();
        weatherDAO = weatherDatabase.weatherDAO();
    }

    public synchronized static Repository getInstance(Application application) {
        if (repository == null) {
            if (repository == null) {
                repository = new Repository(application);
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
                    CurrentWeather currentWeather = response.body().getCurrentWeather();
                    Log.d("repCT", response.body().getCurrentWeather().getTemperature().toString());
                    currentWeatherData.setValue(response.body().getCurrentWeather());
                    weatherDAO.addCurrentData(currentWeather);
//                    Log.d("repDaoCT", weatherDAO.getAllCurrentData().getTemperature().toString());
//                    try {
//                        if (weatherDAO.getAllCurrentData().getValue().getTemperature().toString() != null) {
//                            weatherDAO.removeAllCurrentData();
//                            weatherDAO.addCurrentData(currentWeather);
//                            Log.d("repCT", currentWeather.getTemperature().toString());
//                        } else {
//                            weatherDAO.addCurrentData(response.body().getCurrentWeather());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                try {
                    if (weatherDAO.getAllCurrentData().getTemperature().toString() != null) {
                        currentWeatherData.setValue(weatherDAO.getAllCurrentData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                    int dailyNumber = response.body().getDailyWeather().getData().size();
                    for (int i = 0; i < dailyNumber; i++) {
                        weatherDAO.addDailyData(response.body().getDailyWeather().getData().get(i));
                    }
                    Log.d("repDT", response.body().getDailyWeather().getData().get(0).getTemperatureHigh().toString());
//                    try {
//                        if (weatherDAO.getAllDailyData().getValue().get(0).getTemperatureHigh().toString() != null) {
//                            weatherDAO.removeAllDailyData();
//                            int dailyNumber = response.body().getDailyWeather().getData().size();
//                            for (int i = 0; i < dailyNumber; i++) {
//                                weatherDAO.addDailyData(response.body().getDailyWeather().getData().get(i));
//                            }
//                            Log.d("repDT", response.body().getDailyWeather().getData().get(0).getTemperatureHigh().toString());
//                        } else {
//                            weatherDAO.addCurrentData(response.body().getCurrentWeather());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                try {
                    if (weatherDAO.getAllDailyData().getValue().get(0).getTemperatureHigh().toString() != null) {
                        dailyWeatherData.setValue(weatherDAO.getAllDailyData().getValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return dailyWeatherData;
    }
}
