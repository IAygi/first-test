package ru.iaygi.api.data;

public class Success {
    private Integer id;
    private String token;

    public Success() {

    }

    public Success(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
