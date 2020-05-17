package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of custom session page
 */
public class CustomSessionPage extends BasePage<CustomSessionPage> {

    private final By search = By.xpath("//*[@data-e2e-test-id='filterModalSearch']//*[@placeholder='Search…']");
    private final By doneButton = By.xpath("//*[@data-e2e-test-id='undefined']//*[contains(text(),'Done')]");
    private final By inputQuestion = By.xpath("//*[@data-e2e-test-id='inputQuestionNumber']//input[@type='text']");
    private final By selectExamSimulation = By.name("examSimulation");
    private final By resetButton = By.xpath("//*[@data-e2e-test-id='CustomSessionPage']//*[@data-e2e-test-id='undefined']");
    private final By startButton = By.xpath("//*[@data-e2e-test-id='buttonCreateSession']//*[@data-e2e-test-id='undefined']");
    private final By imageQuestionsToggleButton = By.xpath("//*[@data-e2e-test-id='widgetRow']//*[@type='checkbox']");
    private final By titleToggleButton = By.xpath("//*[@data-e2e-test-id='automatic-title-toggle']//*[@type='checkbox']");
    private final By titleTextField = By.xpath("//*[@data-e2e-test-id='modalInput']");

    private static final String checkboxSelection = "//*[@data-e2e-test-id='checkbox-%s']";
    private static final String iconSelection = "//*[@data-e2e-test-id='icon-%s']";
    private static final String selectSearchTopic = "//*[@data-e2e-test-id='row-%s']//*[@data-e2e-test-id='checkbox-neutral_checked']";

    @Step("Select {searchInput} from the list of {topic}")
    public CustomSessionPage selectFromSearchedTopic(String topic, String searchInput) {
        clickTopic(topic);
        searchForInputTopic(searchInput);
        selectSearchedTopic(searchInput);
        clickDoneButton();
        return this;
    }

    @Step("Select all from the list")
    public CustomSessionPage selectAllOptions(String selectAllTopic) {
        chooseAllOptions(selectAllTopic);
        clickAllOptions();
        clickDoneButton();
        return this;
    }

    @Step("Click top checkbox")
    private void clickAllOptions() {
        findElements(By.xpath("//*[@data-test-id='checkbox']//*[@data-e2e-test-id='checkbox-neutral_checked']"))
                .get(0).click();
    }

    @Step("Click on particular topic")
    private void clickTopic(String topic) {
        findElement(By.xpath(String.format(checkboxSelection, topic))).click();
    }

    @Step("Click all topic from the list {selectAllTopic}")
    private void chooseAllOptions(String selectAllTopic) {
        findElement(By.xpath(String.format(iconSelection, selectAllTopic))).click();
    }

    @Step("Search for particular topic of question {searchInput}")
    private void searchForInputTopic(String searchInput) {
        clearAndFill(search, searchInput);

    }

    @Step("Select the searched topic {searchInput}")
    private void selectSearchedTopic(String searchInput) {
        findElement(By.xpath(String.format(selectSearchTopic, searchInput))).click();
    }

    @Step("Click on done button")
    public void clickDoneButton() {
        findElement(doneButton).click();
    }

    @Step("Enter number of question for the custom session exam")
    public CustomSessionPage enterQuestions(int noOfQuestions) {
        click(inputQuestion);
        clearAndFill(inputQuestion, Integer.toString(noOfQuestions));
        return this;
    }

    @Step("Click title toggle button to enter custom session title")
    private void clickTitleToggleButton() {
        click(titleToggleButton);
    }

    @Step("Click image questions toggle button to select only image questions")
    public CustomSessionPage clickImageQuestionsToggleButton() {
        waitForVisibilityOf(imageQuestionsToggleButton);
        click(imageQuestionsToggleButton);
        return this;
    }

    @Step("Enter new custom session title")
    private void enterTitle(String title) {
        clearAndFill(titleTextField, title);
    }

    @Step("Enter custom session exam title name")
    public CustomSessionPage customSessionTitleName(String title) {
        clickTitleToggleButton();
        enterTitle(title);
        return this;
    }

    @Step("Reset button to reset the options to default")
    public CustomSessionPage resetOptions() {
        click(resetButton);
        return this;
    }

    @Step("Select exam radio button for simulate exam")
    public CustomSessionPage selectExam() {
        waitForVisibilityOf(selectExamSimulation);
        click(selectExamSimulation);
        return this;
    }

    @Step("Click on start to begin the exam")
    public ExamPage clickStartExam() {
        scrollAndClick(startButton);
        waitForInVisibilityOf(startButton);
        getWait().until(urlContains("exam"));
        return new ExamPage();
    }

    public int getNoOfQuestions() {
        return Integer.parseInt(findElement(inputQuestion).getAttribute("value"));
    }

    public boolean isExamModeSelected() {
        return findElement(selectExamSimulation).isSelected();
    }
}
