package dev.washwise.weather.provider;

import dev.washwise.weather.model.WeatherConditions;

public interface WeatherProvider {

    WeatherConditions getCurrentConditions();
}
