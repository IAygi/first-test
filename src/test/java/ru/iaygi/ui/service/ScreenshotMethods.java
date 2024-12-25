package ru.iaygi.ui.service;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScreenshotMethods {

    private static final Path PATH = Path.of("src/test/resources/img/");

    public void simpleScreenshot(SelenideElement selenideElement, String imageName, boolean toCreate) throws Exception {
        SelenideElement element = selenideElement.should(exist, Duration.ofMillis(10_000));
        sleep(500);
        BufferedImage diffImage;
        BufferedImage actualImage;
        BufferedImage expectedImage;
        ImageDiff diff;
        File actualFile = element.getScreenshotAs(OutputType.FILE);
        actualImage = ImageIO.read(actualFile);

        if (toCreate) {
            new File(PATH + "/expected/").mkdirs();
            FileUtils.copyFile(actualFile, new File(PATH + "/expected/" + imageName));
        }

        File expectedFile = new File(PATH + "/expected/" + imageName);
        expectedImage = ImageIO.read(expectedFile);
        diff = new ImageDiffer().makeDiff(expectedImage, actualImage);

        if (diff.hasDiff()) {
            diffImage = diff.getMarkedImage();
            new File(PATH + "/different/").mkdirs();
            File diffFile = new File(PATH + "/different/" + imageName);
            ImageIO.write(diffImage, "png", diffFile);

            new File(PATH + "/actual/").mkdirs();
            FileUtils.copyFile(actualFile, new File(PATH + "/actual/" + imageName));

            attachImageToAllure(imageName);
        }

        assertFalse(diff.hasDiff());
    }

    public void screenshotWithExclude(SelenideElement selenideElement, Set<By> webElements,
                                      String imageName, boolean toCreate) throws Exception {
        SelenideElement element = selenideElement.should(exist, Duration.ofMillis(10_000));
        sleep(500);
        WebDriver driver = WebDriverRunner.getWebDriver();
        ImageDiff diff;
        BufferedImage diffImage;
        BufferedImage actualImage;
        Screenshot expectedScreenshot;
        File actualFile = element.getScreenshotAs(OutputType.FILE);
        actualImage = ImageIO.read(actualFile);

        if (toCreate) {
            new File(PATH + "/expected/").mkdirs();
            FileUtils.copyFile(actualFile, new File(PATH + "/expected/" + imageName));
        }

        Screenshot actualScreenshot = new AShot()
                .ignoredElements(webElements)
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, element);

        actualScreenshot.setImage(actualImage);
        expectedScreenshot = new Screenshot(ImageIO.read(new File(PATH + "/expected/" + imageName)));
        expectedScreenshot.setIgnoredAreas(actualScreenshot.getIgnoredAreas());
        diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

        if (diff.hasDiff()) {
            diffImage = diff.getMarkedImage();
            new File(PATH + "/different/").mkdirs();
            File diffFile = new File(PATH + "/different/" + imageName);
            ImageIO.write(diffImage, "png", diffFile);

            new File(PATH + "/actual/").mkdirs();
            ImageIO.write(actualImage, "PNG", new File(PATH + "/actual/" + imageName));

            attachImageToAllure(imageName);
        }

        assertFalse(diff.hasDiff());
    }

    public void cropScreenshot(SelenideElement selenideElement, String imageName, Map crop, boolean toCreate) throws Exception {
        SelenideElement element = selenideElement.should(exist, Duration.ofMillis(10_000));
        sleep(500);
        ImageDiff diff;
        BufferedImage diffImage;
        BufferedImage actualImage;
        BufferedImage expectedImage;
        File actualFile = element.getScreenshotAs(OutputType.FILE);
        actualImage = ImageIO.read(actualFile);
        int widthImage = actualImage.getWidth() - (Integer) crop.get("w");
        int heightImage = actualImage.getHeight() - (Integer) crop.get("h");
        actualImage = actualImage.getSubimage((Integer) crop.get("x"), (Integer) crop.get("y"),
                widthImage, heightImage);

        if (toCreate) {
            new File(PATH + "/expected/").mkdirs();
            File expectedFile = new File(PATH + "/expected/" + imageName);
            ImageIO.write(actualImage, "png", expectedFile);
        }

        File expectedFile = new File(PATH + "/expected/" + imageName);
        expectedImage = ImageIO.read(expectedFile);
        diff = new ImageDiffer().makeDiff(expectedImage, actualImage);

        if (diff.hasDiff()) {
            diffImage = diff.getMarkedImage();
            new File(PATH + "/different/").mkdirs();
            File diffFile = new File(PATH + "/different/" + imageName);
            ImageIO.write(diffImage, "png", diffFile);

            new File(PATH + "/actual/").mkdirs();
            FileUtils.copyFile(actualFile, new File(PATH + "/actual/" + imageName));

            attachImageToAllure(imageName);
        }

        assertFalse(diff.hasDiff());
    }

    private void attachImageToAllure(String imageName) throws Exception {
        File expectedFile = new File(PATH + "/expected/" + imageName);
        byte[] expectedImage = Files.readAllBytes(expectedFile.toPath());
        saveScreenshot("Expected / " + imageName, expectedImage);

        File actualFile = new File(PATH + "/actual/" + imageName);
        byte[] actualImage = Files.readAllBytes(actualFile.toPath());
        saveScreenshot("Actual / " + imageName, actualImage);

        File difFile = new File(PATH + "/different/" + imageName);
        byte[] difImage = Files.readAllBytes(difFile.toPath());
        saveScreenshot("Different / " + imageName, difImage);
    }

    @Attachment(value = "{name}", type = "image/png")
    private static byte[] saveScreenshot(String name, byte[] image) {
        return image;
    }
}
