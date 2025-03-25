package com.alashko.automation.tests.functional;

import com.alashko.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class LoginAndRegistrationTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private String generatedEmail = "testuser_" + UUID.randomUUID() + "@example.com";
    private final String password = "Password123!";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        loginPage = new LoginPage(driver);
    }

    // ===================== Registration Tests =====================

    @Test(priority = 1, description = "Verify user can successfully register")
    public void verifyUserCanRegister() {
        loginPage.navigateToRegistration();
        loginPage.registerUser("John", "Doe", generatedEmail, password);
        Assert.assertTrue(loginPage.isRegistrationSuccessful(), "User should be registered successfully.");
    }

    // ===================== Login Tests =====================

    @Test(priority = 2, dependsOnMethods = "verifyUserCanRegister", description = "Verify user can log in with valid credentials")
    public void verifyLoginWithValidCredentials() {
        loginPage.attemptLogin(generatedEmail, password);
        Assert.assertTrue(loginPage.isUserLoggedIn(), "User should be logged in successfully.");
    }

    @Test(priority = 3, description = "Verify login with invalid credentials")
    public void verifyLoginWithInvalidCredentials() {
        loginPage.attemptLogin("invalid@example.com", "wrongpassword");
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Error message should be displayed for invalid login.");
    }

    // ===================== Edge Case Tests =====================

    @Test(priority = 4, description = "Edge case: Verify login with empty email")
    public void verifyLoginWithEmptyEmail() {
        loginPage.attemptLogin("", "Password123!");
        Assert.assertTrue(loginPage.isEmailRequiredErrorDisplayed(), "Email error message should be displayed.");
        Assert.assertEquals(loginPage.getEmailRequiredErrorText(), "This is a required field.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


