package dev.washwise.recommendation.engine;

import dev.washwise.recommendation.model.RecommendationLevel;
import dev.washwise.weather.model.WeatherConditions;
import org.springframework.stereotype.Component;

/**
 * Рассчитывает рекомендацию по мойке автомобиля на основании погодных условий
 */
@Component
public class WashRecommendationEngine {

    /**
     * Рассчитывает оценку условий и итоговый уровень рекомендации
     *
     * @param conditions погодные условия для расчёта
     * @return рассчитанная рекомендация
     */
    public WashRecommendation calculate(WeatherConditions conditions) {
        int score = 100;

        int precipitationProbability = conditions.precipitationProbability();

        if (precipitationProbability >= 70) {
            score -= 60;
        } else if (precipitationProbability >= 40) {
            score -= 30;
        }

        if (conditions.temperatureCelsius() <= 0) {
            score -= 30;
        }

        if (conditions.windSpeedKmh() >= 40) {
            score -= 15;
        }

        score = Math.max(0, score);

        RecommendationLevel level = resolveLevel(score);

        return new WashRecommendation(
                score,
                level,
                buildMessage(level)
        );
    }

    private RecommendationLevel resolveLevel(int score) {
        if (score >= 70) {
            return RecommendationLevel.GOOD;
        }

        if (score >= 40) {
            return RecommendationLevel.FAIR;
        }

        return RecommendationLevel.BAD;
    }

    private String buildMessage(RecommendationLevel level) {
        return switch (level) {
            case GOOD -> "Good conditions for a car wash";
            case FAIR -> "Car wash is possible, but conditions are not ideal";
            case BAD -> "It is better to postpone the car wash";
        };
    }
}
