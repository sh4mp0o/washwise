package dev.washwise.recommendation.service;

import dev.washwise.recommendation.engine.WashRecommendationEngine;
import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.model.RecommendationLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecommendationServiceTest {

    private final WashRecommendationEngine recommendationEngine =
            new WashRecommendationEngine();

    private final RecommendationService recommendationService =
            new RecommendationService(recommendationEngine);

    @Test
    void shouldReturnRecommendationCalculatedByEngine() {
        CurrentRecommendationResponse response =
                recommendationService.getCurrentRecommendation();

        assertEquals(100, response.score());
        assertEquals(RecommendationLevel.GOOD, response.level());
        assertEquals(
                "Good conditions for a car wash",
                response.message()
        );
    }
}