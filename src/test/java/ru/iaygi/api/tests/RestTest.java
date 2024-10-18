package ru.iaygi.api.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.iaygi.api.dto.RequestDto;
import ru.iaygi.api.dto.ResponseDelDto;
import ru.iaygi.api.dto.ResponseUserDto;
import ru.iaygi.api.rest.RestMethods;
import ru.iaygi.api.service.UserCreate;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iaygi.api.specification.Conditions.statusCode;

@Severity(NORMAL)
@Owner("iaygi")
@Tags({@Tag("api_test"), @Tag("regression")})
@Epic("Users")
@Feature("Работа с пользователями через API")
@Story("Пользователи")
public class RestTest {

    private final RestMethods restMethods = new RestMethods();
    private final UserCreate userCreate = new UserCreate();
    private RequestDto request;
    private ResponseUserDto response;

    @BeforeEach
    public void prepare() {
        request = userCreate.request();
    }

    @AfterEach
    public void clear() {
        ResponseDelDto responseDelDto = userCreate.responseDelDto(request.getLogin());
        restMethods.deleteUser(responseDelDto).shouldHave(statusCode(204));
    }

    @Test
    @Owner("iaygi")
    @Tag("smoke")
    @DisplayName("Создание пользователя")
    @Description("Проверить создание пользователя")
    void createUser() {
        step("Создать пользователя", () -> {
            response = restMethods.createUser(request).shouldHave(statusCode(201)).getResponseAs(ResponseUserDto.class);
        });

        step("Проверить коттектность данных в ответе", () -> {
            assertThat(response).extracting("login", "name", "surname", "city", "age")
                    .contains(request.getLogin(), request.getLogin(), request.getSurname(), request.getCity(),
                            request.getAge());
        });

        step("Получить пользователя по логину", () -> {
        });

        step("Проверить коттектность данных в ответе", () -> {
        });
    }
}