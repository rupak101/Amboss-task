package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of exam page
 */
public class ExamPage extends BasePage<ExamPage> {

    private final By firstOption = By.xpath("//*[@class='NBOptionInput' and @value='A']");
    private final By proceedToNextItemButton = By.xpath("//*[contains(text(),'Proceed to Next Item')]");
    private final By endBlockButton = By.id("modal-ok");

    @Step("Select a option and click on proceed to next button")
    public ExamPage examSimulation(int questions) {
        for (int i = 0; i < questions; i++) {
            clickFirstOption();
            clickProceedToNextItem();
        }
        return this;
    }

    @Step
    private void clickFirstOption() {
        waitForVisibilityOf(firstOption);
        click(firstOption);
    }

    @Step
    private void clickProceedToNextItem() {
        scrollAndClick(proceedToNextItemButton);
    }

    @Step("Click on end of the block to submit the exam")
    public AnalysisPage clickEndBlockButton() {
        findElement(endBlockButton).click();
        waitForInVisibilityOf(endBlockButton);
        getWait().until(ExpectedConditions.not(urlContains("exam")));
        getWait().until(urlContains("analysis"));
        return new AnalysisPage();
    }
}
