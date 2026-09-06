package dev.washwise.recommendation.engine;

import dev.washwise.recommendation.model.RecommendationLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashRecommendationEngineTest {

    private final WashRecommendationEngine engine =
            new WashRecommendationEngine();

    @Test
    void shouldRecommendWashWhenWeatherIsGood() {
        WeatherConditions conditions = new WeatherConditions(
                10,
                20.0,
                10.0
        );

        WashRecommendation recommendation =
                engine.calculate(conditions);

        assertEquals(100, recommendation.score());
        assertEquals(
                RecommendationLevel.GOOD,
                recommendation.level()
        );
    }

    @Test
    void shouldReduceScoreWhenRainProbabilityIsHigh() {
        WeatherConditions conditions = new WeatherConditions(
                80,
                20.0,
                10.0
        );

        WashRecommendation recommendation =
                engine.calculate(conditions);

        assertEquals(40, recommendation.score());
        assertEquals(
                RecommendationLevel.FAIR,
                recommendation.level()
        );
    }

    @Test
    void shouldNotReturnNegativeScore() {
        WeatherConditions conditions = new WeatherConditions(
                90,
                -10.0,
                50.0
        );

        WashRecommendation recommendation =
                engine.calculate(conditions);

        assertEquals(0, recommendation.score());
        assertEquals(
                RecommendationLevel.BAD,
                recommendation.level()
        );
    }
}