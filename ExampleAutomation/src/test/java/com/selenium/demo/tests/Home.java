package com.selenium.demo.tests;

import com.selenium.demo.base.Driver;
import com.selenium.demo.helpers.Helpers;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Home extends Driver {

    Helpers helper;

    @Test(priority = 1)
    public void searchForAProduct() {
        helper = new Helpers(driver);

        driver.get("https://www.amazon.in/");
        helper.click(By.id("twotabsearchtextbox"), 5);
        helper.type(By.id("twotabsearchtextbox"), "Iphone 17 Pro Max", 10);

        helper.isDisplayed(By.id("sac-suggestion-row-1"), 5);
        helper.click(By.id("sac-suggestion-row-1"), 5);
        Assert.assertTrue(helper.isDisplayed(By.xpath("//*[text()='Check each product page for other buying options.']"), 5));
    }
    
    @Test(priority = 2)
    public void updateUserLocation() {
        helper = new Helpers(driver);

        driver.get("https://www.amazon.in/");
        
        helper.click(By.id("nav-global-location-popover-link"), 10);
        helper.waitTillVisible(By.cssSelector("[autocomplete='postal-code']"), 15);
        helper.type(By.cssSelector("[autocomplete='postal-code']"), "575029", 10);
        helper.click(By.xpath("//span[normalize-space()='Apply']"), 10);
        helper.waitForTimeout(10);
        helper.waitTillVisible(By.xpath("//span[@id='glow-ingress-line2']"));
        String text = helper.getText(By.xpath("//span[@id='glow-ingress-line2']"));
        Assert.assertTrue(text.contains("575029"));

    }

}