package ru.iaygi.api.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;
import ru.iaygi.api.data.Registration;
import ru.iaygi.api.data.Success;
import ru.iaygi.api.data.TestData;
import ru.iaygi.api.service.Specification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Owner("iaygi")
public class SimpleBuilderTest {
    private static final String url = "https://reqres.in";

    @Test
    public void getUsers() {
        int id = 7;
        String email = "michael.lawson@reqres.in";
        String first_name = "Michael";
        String last_name = "Lawson";
        String avatar = "https://reqres.in/img/faces/7-image.jpg";

        Specification.installSpecification(
                Specification.requestSpecification(url), Specification.responseSpecification200());

        List<TestData> data = given()
                .when()
                .log().all()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .extract()
                .body()
                .jsonPath()
                .getList("data", TestData.class);

        assertThat(data.toArray()).extracting("id", "email", "first_name", "last_name", "avatar")
                .contains(tuple(id, email, first_name, last_name, avatar));
    }

    @Test
    void authTest() {
        Specification.installSpecification(
                Specification.requestSpecification(url), Specification.responseSpecification200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Registration user = new Registration("eve.holt@reqres.in", "pistol");

        var successReg = given()
                .body(user)
                .when()
                .log().all()
                .post("/api/register")
                .then()
                .log().all()
                .extract()
                .as(Success.class);

        assertEquals(id, successReg.getId());
        assertEquals(token, successReg.getToken());
    }
}
