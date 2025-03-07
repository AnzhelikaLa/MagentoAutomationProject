package com.alashko.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for Login
    private final By loginEmail = By.id("email");
    private final By loginPassword = By.id("pass");
    private final By loginButton = By.id("send2");
    private final By loginError = By.cssSelector(".message-error");
    private final By userProfile = By.cssSelector(".customer-welcome"); // Check if user is logged in

    // Locators for Registration (on the same page)
    private final By createAccountButton = By.xpath("//a[contains(@class, 'action create primary')]");
    private final By firstNameField = By.id("firstname");
    private final By lastNameField = By.id("lastname");
    private final By emailField = By.id("email_address");
    private final By passwordField = By.id("password");
    private final By confirmPasswordField = By.id("password-confirmation");
    private final By registerButton = By.cssSelector("button[title='Create an Account']");
    private final By myAccountHeading = By.cssSelector("h1.page-title span"); // Should contain "My Account"

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods for Login
    public void attemptLogin(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmail)).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(password);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    public boolean isUserLoggedIn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userProfile)).isDisplayed();
    }

    public boolean isLoginErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginError)).isDisplayed();
    }

    // Methods for Registration
    public void navigateToRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
    }

    public void registerUser(String firstName, String lastName, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        button.click();
    }

    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeading)).getText().contains("My Account");
    }
}

