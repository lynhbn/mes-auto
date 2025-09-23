package com.mes.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticePage extends BasePage {

    private final By testExceptionLink = By.linkText("Test Exceptions");

    public PracticePage(WebDriver driver) {
        super(driver); // gọi constructor BasePage
    }

    @Step("Click the link")
    public void clickTheLink() {
        click(testExceptionLink);
    }

    @Step("Content text should be displayed correctly")
    public void contentTextShouldDisplay() {
        driver.findElement(By.xpath("//em[text()='Page to reproduce the most common Selenium Exceptions.']"));
    }

}
