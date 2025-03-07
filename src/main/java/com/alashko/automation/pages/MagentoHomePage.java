package com.alashko.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MagentoHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By searchBar = By.id("search");
    private final By searchButton = By.cssSelector("button.action.search");
    private final By searchResults = By.cssSelector(".product-item");

    // Constructor
    public MagentoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods
    public void searchProduct(String productName) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchBox.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public boolean isSearchSuccessful() {
        wait.until(ExpectedConditions.titleContains("Search results"));
        return driver.getTitle().contains("Search results");
    }

    public boolean isSearchBoxDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar)).isDisplayed();
    }

}



