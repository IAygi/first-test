package ru.iaygi.api.artem.rest;
import io.qameta.allure.Step;
import ru.iaygi.api.dto.User;
import ru.iaygi.api.service.RestExecutor;

import static io.restassured.http.ContentType.JSON;

public class ResMethodPatch {
    private final static String BASE_URL = "https://reqres.in";

    @Step("Полное обновление пользователя")
    public RestExecutor patchUser(User body) {
        RestExecutor request = new RestExecutor(BASE_URL)
                .contentType(JSON)
                .pathParam("id", 2)
                .body(body);
        request.patch("/api/users/{id}");
        return request;
    }
}
