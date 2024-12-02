package ru.iaygi.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstUiTest {

    @BeforeAll
    public static void init() {
        Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void testPage() {
        String testText = "Testing and validating REST services in Java is harder than";

        open("https://rest-assured.io/");

        ElementsCollection selenideElements = $$(".container p");
        selenideElements.get(2).should(exist, Duration.ofMillis(3_000)).shouldHave(text(testText));
        $(".container").findAll("p").find(text(testText)).shouldBe(exist);
    }

    @Test
    @Tag("test_one")
    void gpnTest() {
        step("ткрыть главную", () -> {
            open("https://www.gazprom-neft.ru/");
        });

        step("", () -> {
            $("#z-nav-products").should(exist, Duration.ofMillis(3_000)).click();
        });

        step("", () -> {
            $$(".z-nav__col").get(4).findAll("ul li a").find(exactText("Топливные карты"))
                    .should(exist, Duration.ofMillis(3_000)).click();
        });

        step("", () -> {
            String txt = $(".content h1").getText();
            assertEquals(txt, "Топливные карты «ОПТИ 24» для коммерческого транспорта",
                    "Текст не соответствует");
        });
    }
}
