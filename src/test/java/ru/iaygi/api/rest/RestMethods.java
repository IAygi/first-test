package ru.iaygi.api.rest;

import ru.iaygi.api.dto.RequestDto;
import ru.iaygi.api.dto.RequestDelDto;
import ru.iaygi.api.specification.RestExecutor;

import static io.restassured.http.ContentType.JSON;

public class RestMethods {

    private final static String BASE_URL = "https://tatyana-aygi.ru";

    public RestExecutor createUser(RequestDto body) {
        RestExecutor request = new RestExecutor(BASE_URL)
                .contentType(JSON)
                .body(body);
        request.post("/test_api/add_user.php");

        return request;
    }

    public RestExecutor deleteUser(RequestDelDto body) {
        RestExecutor request = new RestExecutor(BASE_URL)
                .contentType(JSON)
                .body(body);
        request.delete("/test_api/delete_user.php");

        return request;
    }
}
