package com.example.astroweather.presenter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.astroweather.adapter.ForecastAdapter;
import com.example.astroweather.api.pojo.weather.Channel;
import com.example.astroweather.api.pojo.weather.Forecast;
import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.fragment.ForecastFragment;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.SettingsUpdatedCallback;

import java.util.List;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class ForecastPresenter implements Presenter<ForecastFragment>, SettingsUpdatedCallback {

    private ForecastFragment view;
    private Context context;
    private ApplicationSettings settings = ApplicationSettings.getInstance();

    public ForecastPresenter(Context context) {
    }

    @Override
    public void onCreate() {
        settings.registerForUpdates(this);
        WeatherData weatherData = settings.getWeatherData();
        int numberOfRows;
        try {
            numberOfRows = Integer.parseInt(view.getTag());
        } catch (NumberFormatException e) {
            numberOfRows = 2;
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, numberOfRows);
        view.days.setLayoutManager(layoutManager);


        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            List<Forecast> forecast = data.item.forecast;
            ForecastAdapter adapter = new ForecastAdapter(forecast);
            view.days.setAdapter(adapter);
        }
    }

    @Override
    public void attachView(ForecastFragment view) {
        this.view = view;
    }

    @Override
    public void onSettingsUpdate() {
        WeatherData weatherData = ApplicationSettings.getInstance().getWeatherData();
        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            List<Forecast> forecast = data.item.forecast;
            ForecastAdapter adapter = new ForecastAdapter(forecast);
            view.days.setAdapter(adapter);
        }
    }

    public void onDestroy() {
        ApplicationSettings.getInstance().unregisterForUpdates(this);
    }
}
