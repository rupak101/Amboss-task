package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of analysis page
 */
public class AnalysisPage extends BasePage<AnalysisPage> {

    private final By analysisSession = By.xpath("//*[@data-e2e-test-id='Session analysisPage']");

    @Step("Validate exam analysis page")
    public boolean validateAnalysisPage() {
        waitForVisibilityOf(analysisSession);
        getWait().until(ExpectedConditions.not(urlContains("qbank")));
        getWait().until(urlContains("analysis"));
        return true;
    }
}
