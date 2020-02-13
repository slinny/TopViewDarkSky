package com.example.android.topviewdarksky.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.DatabaseConfiguration;

import java.util.List;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private WeatherDAO weatherDAO;
    private ApiService apiService;
    private LiveData<CurrentWeather> mCurrentWeather;
    private LiveData<List<DailyWeatherData>> mDailyWeatherDataList;

    private static Repository repository;

    public Repository(Application application) {
        WeatherDatabase weatherDatabase = WeatherDatabase.getInstance(application);
        apiService = RetrofitCall.getAllWeather();
        weatherDAO = weatherDatabase.weatherDAO();
        mCurrentWeather = weatherDAO.getAllCurrentData();
        mDailyWeatherDataList = weatherDAO.getAllDailyData();
    }

    public synchronized static Repository getInstance(Application application) {
        if (repository == null) {
            if (repository == null) {
                repository = new Repository(application);
            }
        }
        return repository;
    }

    LiveData<CurrentWeather> getCurrentWeather(){return mCurrentWeather;}
    LiveData<List<DailyWeatherData>> getDailyWeatherDataList(){return mDailyWeatherDataList;}

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
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        return currentWeatherData;
    }

    public void insertCurrentWeather(CurrentWeather currentWeather){
        new insertAsyncTask(weatherDAO).execute(currentWeather);
    }

    private static class insertAsyncTask extends AsyncTask<CurrentWeather, Void, Void> {

        private WeatherDAO mAsyncTaskCurrentDao;

        insertAsyncTask(WeatherDAO dao) {
            mAsyncTaskCurrentDao = dao;
        }

        @Override
        protected Void doInBackground(final CurrentWeather... params) {
            mAsyncTaskCurrentDao.addCurrentData(params[0]);
            return null;
        }
    }

    private static class deleteAllCurrentAsyncTask extends AsyncTask<Void, Void, Void> {

        private WeatherDAO mAsyncTaskCurrentDao;

        deleteAllCurrentAsyncTask(WeatherDAO dao) {
            mAsyncTaskCurrentDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskCurrentDao.removeAllCurrentData();
            return null;
        }
    }

    public MutableLiveData<List<DailyWeatherData>> dailyAPICall(Double lat, Double lon) {
        final MutableLiveData<List<DailyWeatherData>> dailyWeatherData = new MutableLiveData<>();
        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call,
                                   Response<Weather> response) {
                if (response.isSuccessful()) {
                    dailyWeatherData.setValue(response.body().getDailyWeather().getData());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        return dailyWeatherData;
    }

    public void insertAllDailyWeather(DailyWeatherData dailyWeatherData){
        new insertDailyAsyncTask(weatherDAO).execute(dailyWeatherData);
    }

    private static class insertDailyAsyncTask extends AsyncTask<DailyWeatherData, Void, Void> {

        private WeatherDAO mAsyncTaskDailyDao;

        insertDailyAsyncTask(WeatherDAO dao) {
            mAsyncTaskDailyDao = dao;
        }

        @Override
        protected Void doInBackground(final DailyWeatherData... params) {
            mAsyncTaskDailyDao.addDailyData(params[0]);
            return null;
        }
    }

    private static class deleteAllDailyAsyncTask extends AsyncTask<Void, Void, Void> {

        private WeatherDAO mAsyncTaskDailyDao;

        deleteAllDailyAsyncTask(WeatherDAO dao) {
            mAsyncTaskDailyDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDailyDao.removeAllDailyData();
            return null;
        }
    }


}
