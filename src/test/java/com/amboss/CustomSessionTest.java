package com.amboss;

import com.base.BaseTest;
import com.pages.AnalysisPage;
import com.pages.CustomSessionPage;
import com.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.framework.driverconfiguration.DriverCreator.getDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test cases for custom questions session for exam
 */
@Owner("Rupak Mansingh")
@Feature("Create custom exam session as per study goal")
public class CustomSessionTest extends BaseTest {

    private static final int noOfQuestions = 5;
    private static final String customSessionTitle = "Automatic custom session";

    @DataProvider(parallel = true)
    public Object[][] topicAndFilterData() {
        return new Object[][]{
                {"Systems", "Behavioral Health", "Symptoms"},
                {"Symptoms", "Head and neck", "Difficulty"}
        };
    }

    @Test(dataProvider = "topicAndFilterData")
    @Description("Reset custom exam session selected preferences which questions are relevant for upcoming exams based on their study goals")
    public void resetCustomExamSessionPreferences(String topic, String searchTopicInput, String selectAllTopic) {
        CustomSessionPage customSessionPage = loginAndOpenCustomSession();
        selectOptionsAndAddFilter(topic, searchTopicInput, selectAllTopic)
                .resetOptions();
        assertThat("Number questions didn't reset", customSessionPage.getNoOfQuestions(), is(not(noOfQuestions)));
        assertThat("Exam mode didn't reset", customSessionPage.isExamModeSelected(), is(false));
    }

    @Test
    @Description("Start a new custom exam session to test the knowledge without selecting any options")
    public void startNewCustomExamSessionByDefaultOptions() {
        loginAndOpenCustomSession();
        selectExamAndEnterQuestions();
        simulateExamAndValidateAnalysisPage();
    }

    @Test(dataProvider = "topicAndFilterData")
    @Description("Start a new custom exam session with select preferences which questions are relevant for upcoming exams based on their study goals")
    public void startNewCustomExamSessionWithPreference(String topic, String searchTopicInput, String selectAllTopic) {
        loginAndOpenCustomSession();
        selectOptionsAndAddFilter(topic, searchTopicInput, selectAllTopic);
        simulateExamAndValidateAnalysisPage();
    }

    @Test
    @Description("Start a new custom exam session by selecting image questions only")
    public void startNewCustomExamSessionByImageQuestions() {
        loginAndOpenCustomSession()
                .clickImageQuestionsToggleButton();
        selectExamAndEnterQuestions();
        simulateExamAndValidateAnalysisPage();
    }

    private CustomSessionPage loginAndOpenCustomSession() {
        return new LoginPage()
                .openURL()
                .loginWithCredential()
                .clickQBank()
                .clickCustomSession();
    }

    private void simulateExamAndValidateAnalysisPage() {
        new CustomSessionPage()
                .clickStartExam()
                .examSimulation(noOfQuestions)
                .clickEndBlockButton();
        assertThat("Analysis page title didn't display", getDriver().getTitle(), is("Session analysis - AMBOSS"));
        assertThat("Analysis page url didn't display", getDriver().getCurrentUrl(), containsString("analysis"));
        assertThat("Analysis page didn't display", new AnalysisPage().validateAnalysisPage(), is(true));
    }

    private CustomSessionPage selectOptionsAndAddFilter(String topic, String searchTopicInput, String selectAllTopic) {
        new CustomSessionPage()
                .selectFromSearchedTopic(topic, searchTopicInput)
                .selectAllOptions(selectAllTopic)
                .customSessionTitleName(customSessionTitle);
        return selectExamAndEnterQuestions();
    }

    private CustomSessionPage selectExamAndEnterQuestions() {
        return new CustomSessionPage()
                .selectExam()
                .enterQuestions(noOfQuestions);
    }
}
