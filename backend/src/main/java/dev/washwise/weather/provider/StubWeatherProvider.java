package dev.washwise.weather.provider;

import dev.washwise.weather.model.WeatherConditions;
import org.springframework.stereotype.Component;

@Component
public class StubWeatherProvider implements WeatherProvider {

    @Override
    public WeatherConditions getCurrentConditions() {
        return new WeatherConditions(
                10,
                20.0,
                10.0
        );
    }
}
