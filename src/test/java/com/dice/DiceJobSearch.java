package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
public static void main(String[] args) throws InterruptedException {
	// set up Chrome driver path
	WebDriverManager.chromedriver().setup();
	// invoke selenium webdriver
	WebDriver driver = new ChromeDriver(); 
	
	String url = "https://www.dice.com/";
	
	driver.get(url);
	
	String actualTitle = driver.getTitle();
	String expectedTitle = "Job Search for Technology Professionals | Dice.com";
	if(actualTitle.equals(expectedTitle)) {
		System.out.println("Step pass.Dice homepage succesfully loaded");
	}else {
		System.out.println("Step fail. Dice homepage did not load successfully");
		throw new RuntimeException("Step fail. Dice homepage did not load successfully");
	}
	// full scrinng
	driver.manage().window().maximize();
	// wait time 
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String keyword = "Selenium";
	driver.findElement(By.id("search-field-keyword")).clear();
	driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
	
	String location = "22304";
	driver.findElement(By.id("search-field-location")).clear();
	driver.findElement(By.id("search-field-location")).sendKeys(location);
	// 
	driver.findElement(By.id("findTechJobs")).click();
	
	String count = driver.findElement(By.id("posiCountId")).getText();
	System.out.println(count);
	int countResult = Integer.parseInt(count.replace(",",""));
	
	if(countResult>0) {
		System.out.println("Keyword :" + keyword+ "search returned" + countResult + "results in"+ location);
	}else {
		System.out.println("step fail:" + keyword+ "search returned" + countResult + "results in"+ location);
	}
	
	System.out.println("test complite"+ LocalDateTime.now());
	
	Thread.sleep(8000);
	driver.close();
}
	
}
