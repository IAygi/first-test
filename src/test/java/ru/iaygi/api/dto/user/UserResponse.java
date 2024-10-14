package ru.iaygi.api.dto.user;

import lombok.Data;

@Data
public class UserResponse {

	private String createdAt;
	private String name;
	private String id;
	private String job;
}