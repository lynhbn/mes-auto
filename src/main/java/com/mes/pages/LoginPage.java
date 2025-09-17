package com.mes.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("submit");

    public LoginPage(WebDriver driver) {
        super(driver); // gọi constructor BasePage
    }

    @Step("Enter user name {username}")
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    @Step("Enter user password {password}")
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    @Step("Click button login")
    public void clickLogin() {
        click(loginButton);
    }
}
