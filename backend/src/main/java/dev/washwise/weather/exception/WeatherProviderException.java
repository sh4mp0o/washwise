package dev.washwise.weather.exception;

/**
 * Сигнализирует об ошибке при получении или обработке погодных данных от внешнего поставщика
 */
public class WeatherProviderException extends RuntimeException {

    /**
     * Создаёт исключение с описанием ошибки
     *
     * @param message описание ошибки
     */
    public WeatherProviderException(String message) {
        super(message);
    }

    /**
     * Создаёт исключение с описанием ошибки и исходной причиной
     *
     * @param message описание ошибки
     * @param cause   исходное исключение
     */
    public WeatherProviderException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}