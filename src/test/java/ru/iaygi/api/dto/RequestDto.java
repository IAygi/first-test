package ru.iaygi.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDto {

	private String city;
	private String surname;
	private String name;
	private String login;
	private Integer age;
}