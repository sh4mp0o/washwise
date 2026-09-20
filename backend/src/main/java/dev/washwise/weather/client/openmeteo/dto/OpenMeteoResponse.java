package dev.washwise.weather.client.openmeteo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Корневая модель ответа Open-Meteo Forecast API, содержащая текущие и почасовые погодные данные
 *
 * @param current текущие погодные показатели
 * @param hourly  почасовые погодные показатели
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoResponse(
        OpenMeteoCurrent current,
        OpenMeteoHourly hourly
) {
}
