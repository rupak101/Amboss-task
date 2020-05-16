package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of login page
 */
public class LoginPage extends BasePage<LoginPage> {

    private final By userNameInput = By.id("signin_username");
    private final By passwordInput = By.id("signin_password");
    private final By loginButton = By.xpath("//*[@class='amboss-field amboss-lastrow']");

    @Step("Enter user name,password and click on login button")
    public HomePage loginWithCredential() {
        loginWithCredential(System.getProperty("username"), System.getProperty("password"));
        return new HomePage();
    }

    private void loginWithCredential(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
        openURL();
    }

    @Step("Enter user name")
    private void enterUserName(String userName) {
        clearAndFill(userNameInput, userName);
    }

    @Step("Enter password")
    private void enterPassword(String password) {
        clearAndFill(passwordInput, password);
    }

    @Step("Click login button")
    private void clickLoginButton() {
        click(loginButton);
    }
}