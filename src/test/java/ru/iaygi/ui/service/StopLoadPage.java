package ru.iaygi.ui.service;

import com.codeborne.selenide.Selenide;
import lombok.SneakyThrows;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

public class StopLoadPage extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        sleep(10_000);
        switchTo().activeElement().sendKeys(Keys.ESCAPE);
    }
}
