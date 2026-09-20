package dev.washwise.weather.provider;

import dev.washwise.weather.model.WeatherConditions;

/**
 * Предоставляет погодные условия, необходимые для расчета рекомендации по мойке автомобиля
 */
public interface WeatherProvider {

    /**
     * Возвращает текущие погодные условия
     *
     * @return текущие погодные условия
     */
    WeatherConditions getCurrentConditions();
}
