package ru.iaygi.ui.objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class PageObject {

    public void authUser(String login, String password) {
            $("#username").setValue(login);
            $("#password").setValue(password);
            $(".radius").click();
    }
}
