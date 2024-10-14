package ru.iaygi.api.rest;

import io.qameta.allure.Step;
import ru.iaygi.api.service.RestExecutor;

import static io.restassured.http.ContentType.JSON;

public class RestMethods {

    @Step("Получить всех пользователей")
    public RestExecutor getAllUsers() {
        RestExecutor request = new RestExecutor("BASE_URL")
                .contentType(JSON);
        request.get("GET_ALL_USERS");

        return request;
    }
}
