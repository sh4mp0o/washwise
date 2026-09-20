package dev.washwise.weather.provider;

import dev.washwise.weather.client.openmeteo.OpenMeteoClient;
import dev.washwise.weather.client.openmeteo.dto.OpenMeteoCurrent;
import dev.washwise.weather.client.openmeteo.dto.OpenMeteoHourly;
import dev.washwise.weather.client.openmeteo.dto.OpenMeteoResponse;
import dev.washwise.weather.exception.WeatherProviderException;
import dev.washwise.weather.model.WeatherConditions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Проверяет преобразование ответа Open-Meteo во внутреннюю погодную модель WashWise
 */
class OpenMeteoWeatherProviderTest {

    private final OpenMeteoClient openMeteoClient =
            mock(OpenMeteoClient.class);

    private final OpenMeteoWeatherProvider weatherProvider =
            new OpenMeteoWeatherProvider(openMeteoClient);

    /**
     * Проверяет преобразование полного ответа Open-Meteo в текущие погодные условия
     */
    @Test
    void shouldMapOpenMeteoResponseToWeatherConditions() {
        OpenMeteoResponse response = new OpenMeteoResponse(
                new OpenMeteoCurrent(
                        18.7,
                        12.4
                ),
                new OpenMeteoHourly(
                        List.of(25)
                )
        );

        given(openMeteoClient.getCurrentWeather())
                .willReturn(response);

        WeatherConditions conditions =
                weatherProvider.getCurrentConditions();

        assertEquals(
                25,
                conditions.precipitationProbability()
        );
        assertEquals(
                18.7,
                conditions.temperatureCelsius()
        );
        assertEquals(
                12.4,
                conditions.windSpeedKmh()
        );
    }

    /**
     * Проверяет отказ от обработки неполного ответа Open-Meteo.
     */
    @Test
    void shouldThrowExceptionWhenRequiredWeatherDataIsMissing() {
        OpenMeteoResponse response = new OpenMeteoResponse(
                new OpenMeteoCurrent(
                        null,
                        12.4
                ),
                new OpenMeteoHourly(
                        List.of(25)
                )
        );

        given(openMeteoClient.getCurrentWeather())
                .willReturn(response);

        assertThrows(
                WeatherProviderException.class,
                weatherProvider::getCurrentConditions
        );
    }
}