package ru.iaygi.api.dasha;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.iaygi.api.dasha.dto.PutUserResponse;
import ru.iaygi.api.dasha.rest.RestMethodsPut;
import ru.iaygi.api.dto.User;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iaygi.api.service.Conditions.statusCode;

@Severity(NORMAL)
@Owner("dnefedova")
@Tag("api_test")
@Tag("regression")
@Epic("Users")
@Feature("Работа с пользователями через API")

public class RestExecutorTest {

    private final RestMethodsPut restMethodsPut = new RestMethodsPut();
    private PutUserResponse putUserResponse;

    @Test
    @DisplayName("Обновление пользователя")
    @Description("Проверить обновление пользователя")

    void updateUser(){
        String name = "morpheus";
        String job = "zion resident";

        var userUpdated = User.builder()
                .name(name)
                .job(job)
                .build();

        step("Обновить пользователя", () -> {
            putUserResponse = restMethodsPut.updateUser(userUpdated).shouldHave(statusCode(200)).getResponseAs(PutUserResponse.class);
        });

        step("Проверка ответа", () -> {
            assertThat(putUserResponse.getName()).isEqualTo(name);
            assertThat(putUserResponse.getJob()).isEqualTo(job);
        });
    }

}
