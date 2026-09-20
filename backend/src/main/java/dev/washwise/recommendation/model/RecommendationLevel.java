package dev.washwise.recommendation.model;

/**
 * Уровень целесообразности мойки автомобиля
 */
public enum RecommendationLevel {
    /**
     * Погодные условия подходят для мойки
     */
    GOOD,
    /**
     * Мойка возмодна, но погодные условия не оптимальны
     */
    FAIR,
    /**
     * Мойку рекомендуется отложить
     */
    BAD
}
