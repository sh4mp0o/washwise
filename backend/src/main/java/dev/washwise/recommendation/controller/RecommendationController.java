package dev.washwise.recommendation.controller;

import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService
    ) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/current")
    public CurrentRecommendationResponse getCurrentRecommendation() {
        return recommendationService.getCurrentRecommendation();
    }
}
