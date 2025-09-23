package com.mes.tests.ui;

import com.mes.listeners.TestListener;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.mes.utils.ConfigReader;
import com.mes.utils.DriverFactory;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeClass
    public void suiteSetup() {
        String env = System.getProperty("env", "dev"); // default = dev
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false")); // default = false
        ConfigReader.loadProperties(env);
        driver = DriverFactory.getDriver(headless);
        TestListener.setDriver(driver);
        baseUrl = ConfigReader.getProperty("baseUrl");
    }

    @BeforeMethod
    public void testSetup() {
        driver.get(baseUrl + "/practice-test-login/");
    }

    @AfterMethod
    public void testTeardown() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @AfterClass
    public void suiteTeardown() {
        DriverFactory.quitDriver();
    }


}
