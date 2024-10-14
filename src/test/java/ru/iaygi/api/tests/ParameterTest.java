package ru.iaygi.api.tests;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

public class ParameterTest {


    private static final String LAT_CHARS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Проверка граничных значений 5-10 латинских символов
     */

    private static Stream<Arguments> validValues() {
        return Stream.of(
                Arguments.of("5 символов", RandomStringUtils.random(5, LAT_CHARS)),
                Arguments.of("6 символов", RandomStringUtils.random(6, LAT_CHARS)),
                Arguments.of("9 символов", RandomStringUtils.random(9, LAT_CHARS)),
                Arguments.of("10 символов", RandomStringUtils.random(10, LAT_CHARS))
        );
    }

    @ParameterizedTest(name = "Создание пользователя с валидным логином: {0}")
    @MethodSource("validValues")
    @DisplayName("ParameterizedTest: ")
    @Description("Проверить, что пользователь создаётся с валидным логином")
    void createUserWithValidLogin(String key, String value) {
        step("Проверка количества символов: " + key + " -> " + value, () -> {

        });
    }
}
