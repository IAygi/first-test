package ru.iaygi.api;

import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    private final static String BASE_URL = "https://reqres.in";

    @Test
    @Tag("smoke")
    @DisplayName("Получение пользователя")
    @Description("Проверить корректное получение пользователя")
    void getUser() {
        given().
                contentType(ContentType.JSON).
                when().
                get(BASE_URL + "/api/users/2").
                then().
                assertThat().statusCode(200).
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).body("data.last_name", equalTo("Weaver"));
    }
}



