package dev.washwise.recommendation.engine;

public record WeatherConditions(
        int precipitationProbability,
        double temperatureCelsius,
        double windSpeedKmh
) {
}
