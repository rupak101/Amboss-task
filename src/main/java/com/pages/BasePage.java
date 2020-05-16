package com.pages;

import com.framework.driverconfiguration.DriverCreator;
import com.framework.libraries.FileReaderLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.framework.driverconfiguration.DriverCreator.getDriver;

public class BasePage<T> {

    @Step("Find element: {locator}")
    protected static WebElement findElement(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibilityOf(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static Boolean waitForInVisibilityOf(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Step("Find elements: {locator}")
    public static List<WebElement> findElements(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Step("Clear and fill : {locator}")
    public static void clearAndFill(By locator, String text) {
        WebElement element = findClickableElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    @Step("Click on locator  : {locator}")
    public static void click(By locator) {
        findClickableElement(locator).click();
    }

    private static WebElement findClickableElement(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 10, 30);
    }

    public static void scrollAndClick(By locator) {
        ((JavascriptExecutor) DriverCreator.getDriver()).
                executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
        click(locator);
    }

    @Step("Open base URL")
    public T openURL() {
        String url = FileReaderLibrary.getInstance().getConfigReader().getBaseUrl();
        load(url);
        return (T) this;
    }

    public static void load(String url) {
        getDriver().get(url);
    }
}