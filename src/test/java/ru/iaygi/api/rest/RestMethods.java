package ru.iaygi.api.rest;

import io.qameta.allure.Step;
import ru.iaygi.api.dto.User;
import ru.iaygi.api.service.RestExecutor;

import static io.restassured.http.ContentType.JSON;

public class RestMethods {

    private final static String BASE_URL = "https://reqres.in";

    @Step("Получить пользователя")
    public RestExecutor getUser() {
        RestExecutor request = new RestExecutor(BASE_URL)
                .contentType(JSON)
                .pathParam("id", 2);
        request.get("/api/users/{id}");

        return request;
    }

    @Step("Получить пользователя")
    public RestExecutor createUser(User body) {
        RestExecutor request = new RestExecutor(BASE_URL)
                .contentType(JSON)
                .body(body);
        request.post("/api/users");

        return request;
    }
}
