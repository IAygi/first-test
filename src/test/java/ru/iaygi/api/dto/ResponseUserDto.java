package ru.iaygi.api.dto;

import lombok.Data;

@Data
public class ResponseUserDto {

	private String city;
	private String surname;
	private String name;
	private String login;
	private Integer age;
}