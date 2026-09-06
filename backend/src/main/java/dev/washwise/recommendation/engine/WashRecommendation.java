package dev.washwise.recommendation.engine;

import dev.washwise.recommendation.model.RecommendationLevel;

public record WashRecommendation(
        int score,
        RecommendationLevel level,
        String message
) {
}
