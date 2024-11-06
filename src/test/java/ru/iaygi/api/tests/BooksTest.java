package ru.iaygi.api.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.*;
import ru.iaygi.api.rest.BooksRest;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Severity(NORMAL)
@Owner("iaygi")
public class BooksTest {

    private final BooksRest booksRest = new BooksRest();

    @BeforeEach
    public void prepare() {
    }

    @AfterEach
    public void clear() {
    }

    @Test
    @Owner("iaygi")
    @Tag("smoke")
    @DisplayName("")
    @Description("")
    void createUser() {
        step("Создать пользователя", () -> {
            booksRest.getBooks();
        });
    }
}