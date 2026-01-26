package com.selenium.demo.helpers

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 5;

    public DriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL(String url){
        driver.get(url);
    }

    // Custom wait for element to be visible
    public WebElement waitTillVisible(By locator, int timeoutInseconds) {
        int timeout = (timeoutInseconds != null) ? timeoutInseconds : DEFAULT_TIMEOUT;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }

    // Custom wait for element to be clicked
    public WebElement waitTillClickable(By locator, int timeoutInseconds) {
        int timeout = (timeoutInseconds != null) ? timeoutInseconds : DEFAULT_TIMEOUT;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator))
    }

    public void type(By locator, String value, int timeout) {
        WebElement element = waitTillVisible(locator, timeout);
        element.clear();
        element.sendKeys(value)
    }

    public void click(By locator, int timeout) {
        waitTillClickable(locator, timeout)
    };

    public String getText(By locator, int timeout) {
        return waitTillVisible(locator, timeout),getText();
    }

    public boolean isDisplayed(By locator, int timeout) {
        try{
            return waitTillVisible(locator, timeout).isDisplayed();
        }
        catch (Exception e){
            return false
        }
    }
}