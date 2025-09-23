package com.mes.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavBar {
    private WebDriver driver;

    private final By homeMenu = By.linkText("HOME");
    private final By practiceMenu = By.linkText("PRACTICE");
    private final By coursesMenu   = By.linkText("COURSES");
    private final By blogMenu   = By.linkText("BLOG");
    private final By contactMenu   = By.linkText("CONTACT");

    public NavBar(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open practice screen")
    public PracticePage openPracticeScreen() {
        driver.findElement(practiceMenu).click();
        return new PracticePage(driver);
    }
}
