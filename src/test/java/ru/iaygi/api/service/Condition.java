package ru.iaygi.api.service;

import io.restassured.response.Response;

public interface Condition {
    void check(Response response);
}