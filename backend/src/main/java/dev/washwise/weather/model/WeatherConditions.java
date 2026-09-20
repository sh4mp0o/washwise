package dev.washwise.weather.model;

/**
 * Представляет погодные условия, используемые при расчёте рекомендации по мойке автомобиля
 *
 * @param precipitationProbability вероятность осадков в процентах
 * @param temperatureCelsius       температура воздуха в градусах Цельсия
 * @param windSpeedKmh             скорость ветра в километрах в час
 */
public record WeatherConditions(
        int precipitationProbability,
        double temperatureCelsius,
        double windSpeedKmh
) {
}
