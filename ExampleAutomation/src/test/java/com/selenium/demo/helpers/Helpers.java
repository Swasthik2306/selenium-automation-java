package com.selenium.demo.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {
    WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 5;

    public Helpers(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL(String url){
        driver.get(url);
    }

    // Custom wait (5 seconds) for element to be visible
    public WebElement waitTillVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Custom wait (5 seconds) for element to be clicked
    public WebElement waitTillClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Custom wait for element to be visible
    public WebElement waitTillVisible(By locator, int timeoutInseconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInseconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Custom wait for element to be clicked
    public WebElement waitTillClickable(By locator, int timeoutInseconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInseconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void type(By locator, String value, int timeout) {
        WebElement element = waitTillVisible(locator, timeout);
        element.clear();
        element.sendKeys(value);
    }

    public void type(By locator, String value) {
        WebElement element = waitTillVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void click(By locator, int timeout) {
        waitTillClickable(locator, timeout).click();
    };

    public void click(By locator) {
        waitTillClickable(locator).click();
    };

    public String getText(By locator, int timeout) {
        return waitTillVisible(locator, timeout).getText();
    }

    public String getText(By locator) {
        return waitTillVisible(locator).getText();
    }

    public boolean isDisplayed(By locator, int timeout) {
        try{
            return waitTillVisible(locator, timeout).isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }

    public void waitForTimeout(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting", e);
        }
    }

}