package dev.washwise.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфигурационные параметры интеграции с Open-Meteo
 *
 * @param baseUrl   базовый URL Open-Meteo API
 * @param latitude  широта точки, для которой запрашивается погода
 * @param longitude долгота точки, для которой запрашивается погода
 */
@ConfigurationProperties(prefix = "weather.open-meteo")
public record OpenMeteoProperties(
        String baseUrl,
        double latitude,
        double longitude
) {
}
