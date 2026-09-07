package dev.washwise.recommendation.service;

import dev.washwise.recommendation.engine.WashRecommendationEngine;
import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.model.RecommendationLevel;
import dev.washwise.weather.model.WeatherConditions;
import dev.washwise.weather.provider.WeatherProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RecommendationServiceTest {

    private final WeatherProvider weatherProvider =
            mock(WeatherProvider.class);

    private final WashRecommendationEngine recommendationEngine =
            new WashRecommendationEngine();

    private final RecommendationService recommendationService =
            new RecommendationService(
                    weatherProvider,
                    recommendationEngine
            );

    @Test
    void shouldReturnRecommendationForCurrentWeather() {
        given(weatherProvider.getCurrentConditions())
                .willReturn(new WeatherConditions(
                        10,
                        20.0,
                        10.0
                ));

        CurrentRecommendationResponse response =
                recommendationService.getCurrentRecommendation();

        assertEquals(100, response.score());
        assertEquals(
                RecommendationLevel.GOOD,
                response.level()
        );
        assertEquals(
                "Good conditions for a car wash",
                response.message()
        );
    }
}