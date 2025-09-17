package com.mes.tests;
import com.mes.pages.LoginPage;
import com.mes.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;


public class LoginTest extends BaseTest {

    @DataProvider(name = "loginDataFromFile")
    public Iterator<Object[]> getLoginData() throws IOException {
        return TestDataReader.getLoginData();
    }

    @DataProvider(name = "loginDataOnScreen")
    public Object[][] loginData() {
        return new Object[][]{
                {"user1", "pass1", false},
                {"user2", "wrongpass", false},
                {"student","Password123", true}
        };
    }

    @Test(description = "Verify user can login successfully with fixed data", groups = {"smoke", "login"})
    public void testSimpleLogin()  {
        driver.get(baseUrl + "/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("student");
        loginPage.enterPassword("Password1234");
        loginPage.clickLogin();
        loginPage.sleep(3);
        String actualUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, baseUrl + "/logged-in-successfully/");
    }

    @Test(description = "Verify user can login successfully with on screen data", dataProvider = "loginDataOnScreen",
            groups = {"smoke", "login"})
    public void testLoginWithOnScreenData(String username, String password, boolean loginStatus) {
        driver.get(baseUrl + "/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        loginPage.sleep(3);
        String actualUrl = loginPage.getCurrentUrl();
        if (loginStatus){
            Assert.assertEquals(actualUrl, baseUrl + "/logged-in-successfully/");
        }
        else {
            Assert.assertEquals(actualUrl, baseUrl + "/practice-test-login/");
        }

    }

    @Test(description = "Verify user can login successfully with file data", dataProvider = "loginDataFromFile",
            groups = {"smoke", "login"})
    public void testLoginWithFileData(String username, String password, boolean loginStatus) {
        driver.get(baseUrl + "/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        loginPage.sleep(3);
        String actualUrl = loginPage.getCurrentUrl();
        if (loginStatus){
            Assert.assertEquals(actualUrl, baseUrl + "/logged-in-successfully/");
        }
        else {
            Assert.assertEquals(actualUrl, baseUrl + "/practice-test-login/");
        }

    }


}
