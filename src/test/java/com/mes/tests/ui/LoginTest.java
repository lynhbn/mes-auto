package com.mes.tests.ui;
import com.mes.api.objects.CustomerDataFactory;
import com.mes.pages.LoginPage;
import com.mes.pages.PracticePage;
import com.mes.utils.TestDataReader;
import io.restassured.response.Response;
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
        loginPage.enterPassword("Password123");
        loginPage.clickLogin();
        loginPage.sleep(3);
        String actualUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, baseUrl + "/logged-in-successfully/");
    }

    @Test(description = "Navigate to other page using Navigator bar", groups = {"smoke", "login"})
    public void testNavigateToAnotherPage()  {
        driver.get(baseUrl + "/practice-test-login/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("student");
        loginPage.enterPassword("Password123");
        loginPage.clickLogin();
        loginPage.sleep(3);
        String actualUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, baseUrl + "/logged-in-successfully/");
        PracticePage practicePage = loginPage.getNavBar().openPracticeScreen();
        practicePage.contentTextShouldDisplay();

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

    @Test(description = "Create a new customer via API then try to login", groups = {"api", "login"})
    public void createDataFromApiThenLogin()  {
//      API part:
        CustomerDataFactory customerDataFactory = new CustomerDataFactory();
        Response customer = customerDataFactory.createBasicCustomer(
                "name", "Customer_" + System.currentTimeMillis(),
                "email", "abc@test.com",
                "address.city", "Hanoi"
        );
        String userName = customer.jsonPath().getString("username");
        String passWord = customer.jsonPath().getString("password");
//      UI part:
        driver.get(baseUrl + "/practice-test-login/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(userName);
        loginPage.enterPassword(passWord);
        loginPage.clickLogin();
        loginPage.sleep(3);
        String actualUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, baseUrl + "/logged-in-successfully/");
    }


}
