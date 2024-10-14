package ru.iaygi.api.tests;

import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.iaygi.api.dto.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    private final static String BASE_URL = "https://reqres.in";

    @Test
    @Tag("smoke")
    @DisplayName("Получение пользователя")
    @Description("Проверить корректное получение пользователя")
    void getUser() {
        String name = "ivan";
        String job = "tester";

        var user = User.builder()
                .name(name)
                .job(job)
                .build();

        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                log().all().
                post(BASE_URL + "/api/users").
                then().
                log().all().
                assertThat().statusCode(201).
                body("name", equalTo(name)).
                body("job", equalTo(job));
    }
}