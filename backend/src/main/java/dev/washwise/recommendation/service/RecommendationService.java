package dev.washwise.recommendation.service;

import dev.washwise.recommendation.engine.WashRecommendation;
import dev.washwise.recommendation.engine.WashRecommendationEngine;
import dev.washwise.recommendation.engine.WeatherConditions;
import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.model.RecommendationLevel;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private final WashRecommendationEngine recommendationEngine;

    public RecommendationService(
            WashRecommendationEngine recommendationEngine
    ) {
        this.recommendationEngine = recommendationEngine;
    }

    public CurrentRecommendationResponse getCurrentRecommendation() {
        WeatherConditions conditions = new WeatherConditions(
                10,
                20.0,
                10.0
        );

        WashRecommendation recommendation = recommendationEngine.calculate(conditions);

        return new CurrentRecommendationResponse(
                recommendation.score(),
                recommendation.level(),
                recommendation.message()
        );
    }
}
