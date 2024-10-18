package ru.iaygi.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDelDto {

	private String login;
}