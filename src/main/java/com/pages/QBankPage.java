package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of qbank page
 */
public class QBankPage extends BasePage {

    private final By customSession = By.xpath("//*[@data-e2e-test-id='L0-secondlevel-Custom Session']");

    @Step("Click on custom session button in qbank section from side menu")
    public CustomSessionPage clickCustomSession() {
        findElement(customSession).click();
        getWait().until(ExpectedConditions.not(urlContains("qbank")));
        getWait().until(urlContains("customsession"));
        return new CustomSessionPage();
    }
}
