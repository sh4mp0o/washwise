package dev.washwise.recommendation.engine;

import dev.washwise.recommendation.model.RecommendationLevel;

/**
 * Представляет результат расчёта recommendation engine.
 *
 * @param score   рассчитанная оценка условий для мойки
 * @param level   уровень рекомендации
 * @param message пояснение результата
 */
public record WashRecommendation(
        int score,
        RecommendationLevel level,
        String message
) {
}
