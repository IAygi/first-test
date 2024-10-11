package ru.iaygi.api.dasha;

import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

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
                log().all().
                get(BASE_URL + "/api/users/2").
                then().
                log().all().
                assertThat().statusCode(200).
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver"));
    }

    @Test
    @Tag("smoke")
    @DisplayName("Получение списка пользователей")
    @Description("Проверить корректное получение списка пользователей")
    void getUsers() {
        given().
                contentType(ContentType.JSON).
                queryParam("page", 2).
                when().
                log().all().
                get(BASE_URL + "/api/users").
                then().
                log().all().
                assertThat().statusCode(200).
                body("data.id", hasItems(7, 8, 9, 10, 11, 12)).
                body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in")).
                body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel")).
                body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"));
    }
}