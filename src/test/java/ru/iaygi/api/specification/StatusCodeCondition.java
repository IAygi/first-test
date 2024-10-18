package ru.iaygi.api.specification;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {

    private final int statusCode;

    @Override
    public void check(Response response) {
        response.then().assertThat().statusCode(statusCode);
    }
}
