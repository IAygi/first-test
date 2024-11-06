package ru.iaygi.api.rest;

import ru.iaygi.api.specification.RestExecutor;

import static io.restassured.http.ContentType.JSON;

public class BooksRest {

    private final static String BASE_URL = "https://demoqa.com";
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImxvbGthIiwicGFzc3dvcmQiOiIxMjM0NUxvbCoiLCJpYXQiOjE3MzA4MjkxNjR9.OwI1lbQ1q4HwnBMiJ4R8soBLidZsqRdbHlXq-BL4JCw";

    public RestExecutor getBooks() {
        RestExecutor request = new RestExecutor(BASE_URL)
                .pathParam("id", "15335327-25f6-45a4-b7a4-a47935e0bcae")
                .header("Authorization", "Bearer " + token)
                .contentType(JSON);
        request.get("/Account/v1/User/{id}");

        return request;
    }
}