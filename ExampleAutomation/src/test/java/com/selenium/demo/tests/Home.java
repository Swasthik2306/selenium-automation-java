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
        helper.click(By.id("twotabsearchtextbox"), 5);
        helper.type(By.id("twotabsearchtextbox"), "Iphone 17 Pro Max", 10);

        helper.isDisplayed(By.id("sac-suggestion-row-1"), 5);
        helper.click(By.id("sac-suggestion-row-1"), 5);
        Assert.assertTrue(helper.isDisplayed(By.xpath("//*[text()='Check each product page for other buying options.']"), 5));

    }

}