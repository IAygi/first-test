package ru.iaygi.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestBaseUi {

    private static RemoteWebDriver driver;

    @Step("Настройка конфигурации браузера")
    public static void initDriver() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());

        ChromeOptions options = new ChromeOptions();
        Configuration.baseUrl = "https://www.bing.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;

        options.setCapability("browserVersion", "124.0");
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability("selenoid:options", new HashMap<String, Object>() {
            {
                put("name", "NCRM UI test -> " +
                        DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
                put("sessionTimeout", "30m");
                put("enableVNC", true);
                put("screenResolution", "1920x1080x24");
                put("env", new ArrayList<String>() {
                    {
                        add("TZ=UTC");
                    }
                });
                put("labels", new HashMap<String, Object>() {
                    {
                        put("manual", "true");
                    }
                });
                put("enableVideo", true);
            }
        });

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.clipboard", 1);
        options.setExperimentalOption("prefs", prefs);

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        driver.manage().window().setSize(new Dimension(1920, 1080));
        WebDriverRunner.setWebDriver(driver);
    }

    @Step("Закрытие драйвера")
    public static void closeDriver(boolean useSelenoid) {
        driver.quit();
    }
}
