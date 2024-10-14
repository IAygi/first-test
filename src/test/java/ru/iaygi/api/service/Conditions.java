package ru.iaygi.api.service;

public class Conditions {

    public static StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }
}
