package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of home page
 */
public class HomePage extends BasePage<HomePage> {

    private final By qBank = By.xpath("//*[@data-e2e-test-id='L0-Qbank']");

    @Step("Click on qbank from side menu")
    public QBankPage clickQBank() {
        click(qBank);
        getWait().until(urlContains("qbank"));
        return new QBankPage();
    }
}
