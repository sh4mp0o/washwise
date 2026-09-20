package dev.washwise.weather.client.openmeteo;

import dev.washwise.weather.client.openmeteo.dto.OpenMeteoResponse;
import dev.washwise.weather.config.OpenMeteoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Проверяет HTTP-взаимодействие {@link OpenMeteoClient} с Open-Meteo API без выполнения реальных
 * сетевых запросов
 */
@RestClientTest(
        value = OpenMeteoClient.class,
        properties = {
                "weather.open-meteo.base-url=https://api.open-meteo.test",
                "weather.open-meteo.latitude=55.1644",
                "weather.open-meteo.longitude=61.4368"
        }
)
@EnableConfigurationProperties(OpenMeteoProperties.class)
class OpenMeteoClientTest {

    @Autowired
    private OpenMeteoClient openMeteoClient;

    @Autowired
    private MockRestServiceServer server;

    /**
     * Проверяет формирование запроса и десериализацию успешного ответа Open-Meteo
     */
    @Test
    void shouldRequestAndDeserializeCurrentWeather() {
        String responseBody = """
                {
                  "current": {
                    "temperature_2m": 18.7,
                    "wind_speed_10m": 12.4
                  },
                  "hourly": {
                    "precipitation_probability": [25]
                  }
                }
                """;

        server.expect(requestTo(startsWith(
                        "https://api.open-meteo.test/v1/forecast"
                )))
                .andExpect(method(HttpMethod.GET))
                .andExpect(queryParam("latitude", "55.1644"))
                .andExpect(queryParam("longitude", "61.4368"))
                .andExpect(queryParam(
                        "current",
                        "temperature_2m,wind_speed_10m"
                ))
                .andExpect(queryParam(
                        "hourly",
                        "precipitation_probability"
                ))
                .andExpect(queryParam("forecast_hours", "1"))
                .andExpect(queryParam("timezone", "auto"))
                .andRespond(withSuccess(
                        responseBody,
                        MediaType.APPLICATION_JSON
                ));

        OpenMeteoResponse response =
                openMeteoClient.getCurrentWeather();

        assertNotNull(response.current());
        assertNotNull(response.hourly());

        assertEquals(
                18.7,
                response.current().temperature()
        );
        assertEquals(
                12.4,
                response.current().windSpeed()
        );
        assertEquals(
                25,
                response.hourly()
                        .precipitationProbability()
                        .getFirst()
        );

        server.verify();
    }
}