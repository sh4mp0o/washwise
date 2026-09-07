package dev.washwise.recommendation.service;

import dev.washwise.recommendation.engine.WashRecommendation;
import dev.washwise.recommendation.engine.WashRecommendationEngine;
import dev.washwise.weather.model.WeatherConditions;
import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.weather.provider.WeatherProvider;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private final WeatherProvider weatherProvider;
    private final WashRecommendationEngine recommendationEngine;

    public RecommendationService(
            WeatherProvider weatherProvider,
            WashRecommendationEngine recommendationEngine
    ) {
        this.weatherProvider = weatherProvider;
        this.recommendationEngine = recommendationEngine;
    }

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
