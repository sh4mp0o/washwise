package dev.washwise.weather.client.openmeteo;

import dev.washwise.weather.client.openmeteo.dto.OpenMeteoResponse;
import dev.washwise.weather.config.OpenMeteoProperties;
import dev.washwise.weather.exception.WeatherProviderException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * HTTP-клиент для получения погодных данных из Open-Meteo Forecast API
 */
@Component
public class OpenMeteoClient {

    private final RestClient restClient;
    private final OpenMeteoProperties properties;

    /**
     * Создает клиент Open-Meteo
     *
     * @param restClientBuilder настроенный Spring Boot builder HTTP-клиента
     * @param properties        параметры интеграции с Open-Meteo
     */
    public OpenMeteoClient(
            RestClient.Builder restClientBuilder,
            OpenMeteoProperties properties
    ) {
        this.restClient = restClientBuilder
                .baseUrl(properties.baseUrl())
                .build();

        this.properties = properties;
    }

    /**
     * Получает текущие погодные условия и вероятность осадков на ближайший прогнозный час
     *
     * @return ответ Open-Meteo с текущими и почасовыми данными
     * @throws WeatherProviderException если Open-Meteo вернул пустой ответ
     */
    public OpenMeteoResponse getCurrentWeather() {
        OpenMeteoResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", properties.latitude())
                        .queryParam("longitude", properties.longitude())
                        .queryParam(
                                "current",
                                "temperature_2m,wind_speed_10m"
                        )
                        .queryParam(
                                "hourly",
                                "precipitation_probability"
                        )
                        .queryParam("forecast_hours", 1)
                        .queryParam("timezone", "auto")
                        .build())
                .retrieve()
                .body(OpenMeteoResponse.class);

        if (response == null) {
            throw new WeatherProviderException("Open-Meteo returned an empty response");
        }

        return response;
    }
}