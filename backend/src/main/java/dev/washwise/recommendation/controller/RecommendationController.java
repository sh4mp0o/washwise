package dev.washwise.recommendation.controller;

import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для получения рекомендаций по мойке автомобиля
 */
@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    /**
     * Создает контроллер рекомендаций
     *
     * @param recommendationService сервис рекомендаций
     */
    public RecommendationController(
            RecommendationService recommendationService
    ) {
        this.recommendationService = recommendationService;
    }

    /**
     * Возвращает рекомендацию для текущих погодных условий
     *
     * @return текущая рекомендация
     */
    @GetMapping("/current")
    public CurrentRecommendationResponse getCurrentRecommendation() {
        return recommendationService.getCurrentRecommendation();
    }
}
