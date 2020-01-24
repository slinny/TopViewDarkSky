package com.example.android.topviewdarksky.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.android.topviewdarksky.MainActivity;
import com.example.android.topviewdarksky.models.CurrentWeather;
import com.example.android.topviewdarksky.models.DailyWeatherData;
import com.example.android.topviewdarksky.models.Weather;
import com.example.android.topviewdarksky.networking.ApiService;
import com.example.android.topviewdarksky.networking.RetrofitCall;
import com.example.android.topviewdarksky.ui.WeatherAdapter;
import com.example.android.topviewdarksky.util.GPSTracker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private int delay = 0;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

//    private double lat = 42.3601;
//    private double lon = -71.0589;

    public static int counter = 0;
    public static Boolean noWifi = true;
    private WeatherDAO weatherDAO;
    private ApiService apiService;
    private static Repository repository;

    GPSTracker gps;
    double latitude;
    double longitude;

    private Repository() {
        apiService = RetrofitCall.getAllWeather();
//        getLocation();
    }

    public synchronized static Repository getInstance() {
        if (repository == null) {
            if (repository == null) {
                repository = new Repository();
            }
        }
        return repository;
    }

//    public void getRemoteWeather(){
//        apiService.getWeather(latitude, longitude).enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                dailyWeatherDataList = response.body().getDailyWeather().getData();
//                currentWeather = response.body().getCurrentWeather();
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//
//    }

    public LiveData<CurrentWeather> addCurrentWeatherData(final Context context) {
        final MutableLiveData<CurrentWeather> currentData = new MutableLiveData<>();

        apiService.getWeather(latitude, longitude).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                CurrentWeather currentWeather = null;
                currentWeather = response.body().getCurrentWeather();

                weatherDAO = WeatherDatabase
                        .getInstance(context)
                        .weatherDAO();
                weatherDAO.removeAllCurrentData();
                weatherDAO.addCurrentData(currentWeather);
                currentData.setValue(currentWeather);
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

    public LiveData<List<DailyWeatherData>> addDailyWeatherData(final Context context) {
        final MutableLiveData<List<DailyWeatherData>> dailyWeatherDataList = new MutableLiveData<>();

        getLocation();
        Log.d("latitude", String.valueOf(latitude));

        apiService.getWeather(latitude, longitude).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                ArrayList<DailyWeatherData> dailyWeatherDataArrayList = new ArrayList<>();

                for (int i = 0; i < response.body().getDailyWeather().getData().size(); i++) {
                    DailyWeatherData dailyWeatherData = new DailyWeatherData(response.body().getDailyWeather().getData().get(i));
                    dailyWeatherDataArrayList.add(dailyWeatherData);
                }
                weatherDAO = WeatherDatabase
                        .getInstance(context)
                        .weatherDAO();

                weatherDAO.removeAllDailyData();
                weatherDAO.insert(dailyWeatherDataArrayList);
                dailyWeatherDataList.setValue(dailyWeatherDataArrayList);
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

    private void getLocation() {
        gps = new GPSTracker(MainActivity.context);

        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Log.d("latitude", String.valueOf(latitude));
            Log.d("longtitude", String.valueOf(longitude));
        } else {
//            gps.showSettingsAlert();
            Log.d("GPSTracker", "getLocation failed");
        }
    }


}
