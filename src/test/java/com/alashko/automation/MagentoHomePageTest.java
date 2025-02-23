package com.alashko.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;

public class MagentoHomePageTest {

    @Test
    public void verifySearchFunctionality() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://magento.softwaretestingboard.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchBar.sendKeys("shirt");
        WebElement searchButton = driver.findElement(By.cssSelector("button.action.search"));
        searchButton.click();

        // Verify results page
        wait.until(ExpectedConditions.titleContains("Search results"));
        Assert.assertTrue(driver.getTitle().contains("Search results"), "Search results should display.");

        driver.quit();
    }

}







