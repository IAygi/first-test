package ru.iaygi.api.specification;

import io.restassured.response.Response;

public interface Condition {
    void check(Response response);
}