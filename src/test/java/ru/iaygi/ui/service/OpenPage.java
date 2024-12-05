package ru.iaygi.ui.service;

import lombok.SneakyThrows;
import static com.codeborne.selenide.Selenide.*;

public class OpenPage extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        open("https://demoqa.com/automation-practice-form");
    }
}
