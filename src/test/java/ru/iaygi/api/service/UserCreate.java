package ru.iaygi.api.service;

import ru.iaygi.api.dto.RequestDto;
import ru.iaygi.api.dto.ResponseDelDto;

import static ru.iaygi.api.data.FakeData.*;

public class UserCreate {

    public RequestDto request() {
        return RequestDto.builder()
                .login(login())
                .name(firstName())
                .surname(lastName())
                .city(cityName())
                .age(number())
                .build();
    }

    public ResponseDelDto responseDelDto(String login) {
        return ResponseDelDto.builder()
                .login(login)
                .build();
    }
}
