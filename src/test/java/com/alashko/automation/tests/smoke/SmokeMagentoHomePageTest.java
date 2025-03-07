package com.alashko.automation.tests.smoke;

import com.alashko.automation.pages.MagentoHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class SmokeMagentoHomePageTest {
    private WebDriver driver;
    private MagentoHomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
        homePage = new MagentoHomePage(driver);
    }

    @Test
    public void verifyHomePageLoads() {
        Assert.assertTrue(driver.getTitle().contains("Home Page"), "Home Page should load correctly.");
    }

    @Test
    public void verifySearchBoxExists() {
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), "Search box should be present on the home page.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}





