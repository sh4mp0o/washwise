package dev.washwise.weather.client.openmeteo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Текущие погодные показатели из Open-Meteo API
 *
 * @param temperature текущая температура воздуха в градусах Цельсия
 * @param windSpeed   текущая скорость ветра в километрах в час
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoCurrent(

        @JsonProperty("temperature_2m")
        Double temperature,

        @JsonProperty("wind_speed_10m")
        Double windSpeed

) {
}
