package ru.iaygi.api.artem;

import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ApiTest {

    private final static String BASE_URL = "https://reqres.in/api";


    @Test
    @Tag("smoke")
    @DisplayName("Проверка роута unknown")
    @Description("Проверить значения параметров в ответе")
    void getUnknownTest() {
        given().
                contentType(ContentType.JSON).
                pathParam("id", 2).
                pathParam("route", "unknown").
                when().
                log().all().
                get(BASE_URL + "/{route}/{id}").
                then().
                log().all().
                assertThat().statusCode(200).
                body("data.id", equalTo(2)).
                body("data.name", equalTo("fuchsia rose")).
                body("data.year", equalTo(2001)).
                body("data.color", equalTo("#C74375")).
                body("data.pantone_value", equalTo("17-2031"));

    }
}