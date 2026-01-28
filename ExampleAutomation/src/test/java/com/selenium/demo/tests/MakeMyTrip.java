package com.selenium.demo.tests;

import com.selenium.demo.base.Driver;
import com.selenium.demo.helpers.Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;;

public class MakeMyTrip extends Driver{
    Helpers helper;

    @Test(priority = 1)
    public void NavigateMakeMyTripPage() {
        helper = new Helpers(driver);

        //Navigate to the make my trip page
        helper.openURL("https://www.makemytrip.com/");
        helper.waitTillVisible(By.cssSelector("[alt='Make My Trip']"), 10);
        helper.waitForTimeout(5);

        //Close the Registration popup
        helper.click(By.cssSelector("[data-cy='closeModal']"));
        helper.waitForTimeout(5);
        helper.click(By.id("top-banner"));
        helper.waitForTimeout(5);
        //Close the help modal
        helper.click(By.cssSelector("[alt='minimize']"));

    }

    @Test(priority = 2, dependsOnMethods = "NavigateMakeMyTripPage")
    public void CheckFlightFromTo() {
        
        //Select the source location airport
        helper.waitForTimeout(3);
        helper.click(By.id("fromCity"));
        helper.waitForTimeout(3);
        helper.type(By.cssSelector("[placeholder='From']"), "Mangalore", 5);
        helper.waitForTimeout(2);
        helper.click(By.xpath("(//*[@class='revampedCityName'])[1]"), 5);

        //Select the destination location airport
        helper.click(By.id("toCity"));
        helper.type(By.cssSelector("[placeholder='To']"), "Bangalore", 5);
        helper.waitForTimeout(2);
        helper.click(By.xpath("(//*[@class='revampedCityName'])[1]"), 5);
        helper.waitForTimeout(5);

        helper.waitTillVisible(By.cssSelector("[class='DayPicker']"), 10);

        //Select the date
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        LocalDate fromDate = LocalDate.now().plusDays(1);
        LocalDate toDate   = fromDate.plusDays(1);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH);

        // Open departure calendar
        js.executeScript("arguments[0].click();",
                driver.findElement(By.cssSelector("[data-cy='departureDate']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".DayPicker")
        ));

        // Select from date
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@aria-label='" + fromDate.format(formatter) + "']")
                )));

        // Open return calendar
        js.executeScript("arguments[0].click();",
                driver.findElement(By.cssSelector("[data-cy='returnArea']")));

        // Select return date
        js.executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@aria-label='" + toDate.format(formatter) + "']")
                )));
        helper.click(By.id("top-banner"));
        helper.waitForTimeout(3);

        // Click Search
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Search']")
        )).click();



    }

    @Test(priority = 3, dependsOnMethods = "CheckFlightFromTo")
    public void ValidateFlightSearchResult() {

        //Validate the search results
        helper.waitForTimeout(5);

        // helper.waitTillVisible(By.xpath("//span[normalize-space()='Flights from Mangalore to Bangalore']"), 10);
    }

}
