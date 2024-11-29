package ru.iaygi.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {

    @Test
    void testPage() {
        String testText = "Testing and validating REST services in Java is harder than";

        Configuration.holdBrowserOpen = true;
        open("https://rest-assured.io/");

        ElementsCollection selenideElements = $$(".container p");
        selenideElements.get(2).should(exist, Duration.ofMillis(3_000)).shouldHave(text(testText));
        $(".container").findAll("p").find(text(testText)).shouldBe(exist);
    }
}
