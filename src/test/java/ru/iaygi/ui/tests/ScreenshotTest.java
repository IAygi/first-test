package ru.iaygi.ui.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.iaygi.ui.service.ScreenshotMethods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ScreenshotTest {

    private final ScreenshotMethods screenshotMethods = new ScreenshotMethods();
    private final boolean TO_CREATE = false;

    @BeforeAll
    public static void init() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
        Configuration.pageLoadStrategy = "none";
    }

    @Test
    void simpleScreenshotTest() {
        String imageName = "headerImage.png";

        step("Открыть главную страницу", () -> {
            open("https://rest-assured.io/");
        });

        step("Шаг", () -> {
            screenshotMethods.simpleScreenshot($("[src=\"img/name-transparent.png\"]"), imageName, TO_CREATE);
        });
    }

    @Test
    void screenshotWithExcludeTest() {
        String imageName = "habrHeaderImage.png";
        Set<By> webElements = new HashSet<>();
        webElements.add(By.className("tm-article-snippet__stats"));

        step("Открыть главную страницу", () -> {
            open("https://habr.com/ru/companies/jetinfosystems/articles/867986/");
            sleep(5_000);
            executeJavaScript("window.stop();");
        });

        step("Шаг", () -> {
            screenshotMethods.screenshotWithExclude($(".tm-article-presenter__header"), webElements, imageName, TO_CREATE);
        });
    }

    @Test
    void cropScreenshotTest() {
        String imageName = "cropImage.png";
        Map <String, Integer> crop = Map.of(
                "w", 150,
                "h", 50,
                "x", 0,
                "y", 0
        );

        step("Открыть главную страницу", () -> {
            open("https://www.tiny.cloud/docs/tinymce/latest/snow-demo/");
            switchTo().frame($("#premiumskinsandicons-snow_ifr"));
        });

        step("Шаг", () -> {
            screenshotMethods.cropScreenshot($("#tinymce"), imageName, crop, true);
        });
    }
}
