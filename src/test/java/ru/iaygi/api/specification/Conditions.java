package ru.iaygi.api.specification;

public class Conditions {

    public static StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }
}
