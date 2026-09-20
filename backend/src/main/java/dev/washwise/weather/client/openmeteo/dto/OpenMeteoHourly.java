package dev.washwise.weather.client.openmeteo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Почасовые погодные показатели из Open-Meteo API
 *
 * @param precipitationProbability почасовая вероятность осадков
 *                                 в процентах
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoHourly(

        @JsonProperty("precipitation_probability")
        List<Integer> precipitationProbability

) {
}
