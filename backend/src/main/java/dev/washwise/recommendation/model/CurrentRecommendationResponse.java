package dev.washwise.recommendation.model;

public record CurrentRecommendationResponse(
        int score,
        RecommendationLevel level,
        String message
) {
}
