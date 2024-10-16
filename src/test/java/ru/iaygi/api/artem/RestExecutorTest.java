package ru.iaygi.api.artem;


import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.iaygi.api.artem.dto.PatchUserResponse;
import ru.iaygi.api.artem.rest.ResMethodPatch;
import ru.iaygi.api.dto.User;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iaygi.api.service.Conditions.statusCode;

@Severity(NORMAL)
@Owner("kolimietc")
@Tag("api_test")
@Tag("regression")
@Epic("Users")
@Feature("Работа с пользователями через API")


public class RestExecutorTest {
    private final ResMethodPatch restMethodsPatch = new ResMethodPatch();
    private PatchUserResponse patchUserResponse;

    @Test
    @DisplayName("Частичное обновление пользователя")
    @Description("Проверить обновление пользователя")

    void patchUser() {
        String name = "Artem";
        String job = null;

        var patchUser = User.builder()
                .name(name)
                .build();

        step("Обновить пользователя", () -> {
            patchUserResponse = restMethodsPatch.patchUser(patchUser).shouldHave(statusCode(200)).getResponseAs(PatchUserResponse.class);
        });

        step("Проверка ответа", () -> {
            assertThat(patchUserResponse.getName()).isEqualTo(name);
            assertThat(patchUserResponse.getJob()).isEqualTo(job);
        });
    }
}


