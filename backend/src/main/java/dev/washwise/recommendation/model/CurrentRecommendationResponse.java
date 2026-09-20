package dev.washwise.recommendation.model;

/**
 * DTO текущей рекомендации, возвращаемой REST API.
 *
 * @param score   оценка условий для мойки от 0 до 100
 * @param level   уровень рекомендации
 * @param message человекочитаемое описание рекомендации
 */
public record CurrentRecommendationResponse(
        int score,
        RecommendationLevel level,
        String message
) {
}
