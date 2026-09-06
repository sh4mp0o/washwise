package dev.washwise.weather.model;

public record WeatherConditions(
        int precipitationProbability,
        double temperatureCelsius,
        double windSpeedKmh
) {
}
