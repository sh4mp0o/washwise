package dev.washwise.weather.provider;

import dev.washwise.weather.client.openmeteo.OpenMeteoClient;
import dev.washwise.weather.client.openmeteo.dto.OpenMeteoCurrent;
import dev.washwise.weather.client.openmeteo.dto.OpenMeteoHourly;
import dev.washwise.weather.client.openmeteo.dto.OpenMeteoResponse;
import dev.washwise.weather.exception.WeatherProviderException;
import dev.washwise.weather.model.WeatherConditions;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link WeatherProvider}, использующая Open-Meteo в качестве внешнего источника
 * погодных данных
 */
@Component
public class OpenMeteoWeatherProvider implements WeatherProvider {
    private final OpenMeteoClient openMeteoClient;

    /**
     * Создает поставщик погодных данных Open-Meteo
     *
     * @param openMeteoClient openMeteo HTTP-клиент Open-Meteo
     */
    public OpenMeteoWeatherProvider(OpenMeteoClient openMeteoClient) {
        this.openMeteoClient = openMeteoClient;
    }

    /**
     * Получает погодные данные Open-Meteo и преобразует их во внутреннюю модель WashWise
     *
     * @return текущие погодные условия
     * @throws WeatherProviderException если ответ Open-Meteo
     *                                  не содержит обязательных данных
     */
    @Override
    public WeatherConditions getCurrentConditions() {
        OpenMeteoResponse response = openMeteoClient.getCurrentWeather();

        OpenMeteoCurrent current = response.current();
        OpenMeteoHourly hourly = response.hourly();

        if (current == null
                || current.temperature() == null
                || current.windSpeed() == null
                || hourly == null
                || hourly.precipitationProbability() == null
                || hourly.precipitationProbability().isEmpty()) {

            throw new WeatherProviderException(
                    "Open-Meteo response does not contain required weather data");
        }

        return new WeatherConditions(hourly.precipitationProbability().getFirst(),
                current.temperature(),
                current.windSpeed());
    }
}
