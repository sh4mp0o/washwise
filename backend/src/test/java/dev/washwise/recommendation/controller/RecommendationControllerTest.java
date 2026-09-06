package dev.washwise.recommendation.controller;

import dev.washwise.recommendation.model.CurrentRecommendationResponse;
import dev.washwise.recommendation.model.RecommendationLevel;
import dev.washwise.recommendation.service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecommendationController.class)
class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecommendationService recommendationService;

    @Test
    void shouldReturnCurrentRecommendation() throws Exception {
        given(recommendationService.getCurrentRecommendation())
                .willReturn(new CurrentRecommendationResponse(
                        85,
                        RecommendationLevel.GOOD,
                        "Good conditions for a car wash"
                ));

        mockMvc.perform(get("/api/v1/recommendations/current"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.score").value(85))
                .andExpect(jsonPath("$.level").value("GOOD"))
                .andExpect(jsonPath("$.message")
                        .value("Good conditions for a car wash"));

        then(recommendationService)
                .should()
                .getCurrentRecommendation();
    }
}