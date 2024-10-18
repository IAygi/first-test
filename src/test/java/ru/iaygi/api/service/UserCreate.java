package ru.iaygi.api.service;

import ru.iaygi.api.dto.RequestDto;
import ru.iaygi.api.dto.RequestDelDto;

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

    public RequestDelDto responseDelDto(String login) {
        return RequestDelDto.builder()
                .login(login)
                .build();
    }
}
