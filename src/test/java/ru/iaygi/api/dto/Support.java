package ru.iaygi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Support{

	@JsonProperty("text")
	private String text;

	@JsonProperty("url")
	private String url;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}