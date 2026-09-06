package dev.washwise.recommendation.service;

import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.model.RecommendationLevel;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public CurrentRecommendationResponse getCurrentRecommendation() {
        return new CurrentRecommendationResponse(
                85,
                RecommendationLevel.GOOD,
                "Good conditions for a car wash"
        );
    }
}
