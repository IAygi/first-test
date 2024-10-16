package ru.iaygi.api.artem.dto;

import lombok.Data;

@Data
public class PatchUserResponse {

    private String name;
    private String job;
    private String updatedAt;

}