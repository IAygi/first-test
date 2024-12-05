package ru.iaygi.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.iaygi.ui.objects.PageObject;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstUiTest extends TestBaseUi {

    PageObject object = new PageObject();

    @SneakyThrows
    @BeforeAll
    public static void init() {
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "none";
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
        step("Открыть главную", () -> {
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

    @Test
    void goToLink() {
        open("https://ria.ru/");
        $$(".footer__rubric-item").get(6).scrollTo().click();
        $("h1 a").shouldHave(exactText("Наука"));
    }

    @Test
    void formTesting() {
        String login = "tomsmith";
        String password = "SuperSecretPassword!";

        step("Открыть страницу с формой", () -> {
            open("https://the-internet.herokuapp.com/login");
        });

        step("Авторизаваться", () -> {
            object.authUser(login, password);
        });
    }

    @Test
    void searchTest() {
        open("https://www.bing.com/");
        $("[name=\"q\"]").setValue("test").pressEnter();
    }

    @Test
    void stopPageLoading() {
        open("https://demoqa.com/login");
        sleep(3_000);
        executeJavaScript("window.stop();");
    }
}
