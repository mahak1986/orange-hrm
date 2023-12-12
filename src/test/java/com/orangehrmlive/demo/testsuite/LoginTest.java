package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(CustomListeners.class)
public class LoginTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddUserPage addUserPage;
    DashboardPage dashboardPage;
    ViewSystemUsersPage viewSystemUsersPage;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        adminPage = new AdminPage();
        addUserPage = new AddUserPage();
        dashboardPage = new DashboardPage();
        viewSystemUsersPage = new ViewSystemUsersPage();
    }

    @Test(groups = {"sanity","regression"})
    public void verifyUserShouldLoginSuccessFully() throws InterruptedException {
        //Enter username
        loginPage.enterUserName("Admin");

        //Enter password
        loginPage.enterPassword("admin123");
        Thread.sleep(2000);

        //Click on Login Button
        loginPage.ClickOnLoginButton();
        Thread.sleep(2000);

        //Click on Dashboard link
        dashboardPage.clickONDashboardLink();
        Thread.sleep(2000);
        //Verify "Dashboard" test
        String expected1 = "Dashboard";
        Assert.assertEquals(dashboardPage.verifyDashboardText(), expected1, "Error Message");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatTheLogoDisplayOnHomePage() {
        // * Verify Logo is Displayed
        loginPage.getLogoElement("Logo HR for All is not displayed on the login page");

        //Login To The application
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.ClickOnLoginButton();

    }

    @Test(groups = {"regression"})
    public void verifyUserShouldLogOutSuccessFully() {
        //Login To The application
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.ClickOnLoginButton();

        //Click on User Profile logo
        homePage.clickOnUserProfileLogo();

        //Mouse hover on "Logout" and click
        loginPage.mouseHoverAndClickOnLogout();

        //Verify the text "Login Panel" is displayed
        loginPage.getTextForLoginPanel("Login");
    }

    @Test(groups = {"regression"})
    public void verifyErrorMessageWithInvalidCredentials() {
        //Enter username <username>
        loginPage.enterUserName("Ad");

        //Enter password <password>
        loginPage.enterPassword("admin12");

        //Click on Login Button
        loginPage.ClickOnLoginButton();

        //Verify Error message <errorMessage>
        loginPage.getErrorMessage("Invalid credentials");

    }
}
