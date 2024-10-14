package ru.iaygi.api.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.iaygi.api.dto.Response;
import ru.iaygi.api.dto.User;
import ru.iaygi.api.dto.user.UserResponse;
import ru.iaygi.api.rest.RestMethods;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iaygi.api.service.Conditions.statusCode;

@Severity(NORMAL)
@Owner("iaygi")
@Tag("api_test")
@Tag("regression")
@Epic("Users")
@Feature("Работа с пользователями через API")
public class RestExecutorTest {

    private final RestMethods restMethods = new RestMethods();
    private Response response;
    private UserResponse responseUser;

    @Test
    @DisplayName("Получение пользователя")
    @Description("Проверить получение пользователя")
    void getUser() {
        step("Получить пользователя", () -> {
            response = restMethods.getUser().shouldHave(statusCode(200)).getResponseAs(Response.class);
        });

        step("Проверка", () -> {
            assertThat(response.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
        });
    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Проверить создание пользователя")
    void createUser() {
        String name = "ivan";
        String job = "tester";

        var user = User.builder()
                .name(name)
                .job(job)
                .build();

        step("Создать пользователя", () -> {
            responseUser = restMethods.createUser(user).shouldHave(statusCode(201)).getResponseAs(UserResponse.class);
        });

        step("Проверка", () -> {
            assertThat(responseUser.getName()).isEqualTo(name);
            assertThat(responseUser.getJob()).isEqualTo(job);
        });
    }
}
