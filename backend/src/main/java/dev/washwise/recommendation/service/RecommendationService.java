package dev.washwise.recommendation.service;

import dev.washwise.recommendation.engine.WashRecommendation;
import dev.washwise.recommendation.engine.WashRecommendationEngine;
import dev.washwise.weather.model.WeatherConditions;
import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.weather.provider.WeatherProvider;
import org.springframework.stereotype.Service;

/**
 * Координирует получение погодных данных и расчет текущей рекомендации по мойке автомобиля
 */
@Service
public class RecommendationService {

    private final WeatherProvider weatherProvider;
    private final WashRecommendationEngine recommendationEngine;

    /**
     * Создает сервис рекомендаций
     *
     * @param weatherProvider      источник погодных данных
     * @param recommendationEngine движок расчёта рекомендаций
     */
    public RecommendationService(
            WeatherProvider weatherProvider,
            WashRecommendationEngine recommendationEngine
    ) {
        this.weatherProvider = weatherProvider;
        this.recommendationEngine = recommendationEngine;
    }

    /**
     * Формирует текущую рекомендацию на основании актуальной погоды
     *
     * @return DTO текущей рекомендации
     */
    public CurrentRecommendationResponse getCurrentRecommendation() {
        WeatherConditions conditions =
                weatherProvider.getCurrentConditions();

        WashRecommendation recommendation =
                recommendationEngine.calculate(conditions);

        return new CurrentRecommendationResponse(
                recommendation.score(),
                recommendation.level(),
                recommendation.message()
        );
    }
}
