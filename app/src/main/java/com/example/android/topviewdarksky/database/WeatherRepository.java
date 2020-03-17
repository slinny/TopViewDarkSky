package com.example.android.topviewdarksky.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.topviewdarksky.MainActivity.latitude;
import static com.example.android.topviewdarksky.MainActivity.longitude;

public class WeatherRepository {

    private WeatherDAO weatherDAO;
    private ApiService apiService;
    private LiveData<CurrentWeather> mCurrentWeather;
    private LiveData<List<DailyWeatherData>> mDailyWeatherDataList;
    private static WeatherRepository repository;

    public WeatherRepository(Application application) {
        WeatherDatabase weatherDatabase = WeatherDatabase.getInstance(application);
        apiService = RetrofitCall.getAllWeather();
        weatherDAO = weatherDatabase.weatherDAO();
        mCurrentWeather = weatherDAO.getCurrentData();
        mDailyWeatherDataList = weatherDAO.getAllDailyData();
    }

    public synchronized static WeatherRepository getInstance(Application application) {
        if (repository == null) {
            if (repository == null) {
                repository = new WeatherRepository(application);
            }
        }
        return repository;
    }

    public LiveData<CurrentWeather> getCurrentWeather() {
        insertCurrentWeather(latitude,longitude);
        return mCurrentWeather;
    }
    public LiveData<List<DailyWeatherData>> getDailyWeatherData() {
        insertDailyWeatherData(latitude,longitude);
        return mDailyWeatherDataList;
    }

    public void insertCurrentWeather(Double lat, Double lon) {
        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call,
                                   Response<Weather> response) {
                if (response.isSuccessful()) {
                    try {
                        new deleteCurrentAsyncTask(weatherDAO).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CurrentWeather currentWeatherTemp = response.body().getCurrentWeather();
                    new insertAsyncTask(weatherDAO).execute(currentWeatherTemp);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<CurrentWeather, Void, Void> {

        private WeatherDAO mAsyncTaskCurrentDao;

        insertAsyncTask(WeatherDAO dao) {
            mAsyncTaskCurrentDao = dao;
        }

        @Override
        protected Void doInBackground(final CurrentWeather... currentWeather) {
            CurrentWeather currentWeather1 = currentWeather[0];
            Log.d("repoAscCT", currentWeather[0].getTemperature());
            mAsyncTaskCurrentDao.insertCurrentData(currentWeather1);
            return null;
        }
    }

    private static class deleteCurrentAsyncTask extends AsyncTask<Void, Void, Void> {
        private WeatherDAO mAsyncTaskDao;

        deleteCurrentAsyncTask(WeatherDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.removeAllCurrentData();
            return null;
        }
    }


    public void insertDailyWeatherData(Double lat, Double lon) {
        apiService.getWeather(lat, lon).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call,
                                   Response<Weather> response) {
                if (response.isSuccessful()) {
                    new deleteAllDailytAsyncTask(weatherDAO).execute();
                    for (int i = 0; i < response.body().getDailyWeather().getData().size(); i++) {
                        DailyWeatherData dailyWeatherDataTemp = response.body().getDailyWeather().getData().get(i);
                        new insertDailyAsyncTask(weatherDAO).execute(dailyWeatherDataTemp);
                    }
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private static class insertDailyAsyncTask extends AsyncTask<DailyWeatherData, Void, Void> {

        private WeatherDAO mAsyncTaskDailyDao;

        insertDailyAsyncTask(WeatherDAO dao) {
            mAsyncTaskDailyDao = dao;
        }

        @Override
        protected Void doInBackground(final DailyWeatherData... dailyData) {
            mAsyncTaskDailyDao.insertDailyData(dailyData[0]);
            return null;
        }
    }
  
    private static class deleteAllDailytAsyncTask extends AsyncTask<Void, Void, Void> {
        private WeatherDAO mAsyncTaskDao;

        deleteAllDailytAsyncTask(WeatherDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.removeAllDailyData();
            return null;
        }
    }
}
