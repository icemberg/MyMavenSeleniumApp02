package com.example; 
 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.chrome.ChromeOptions;

/** 
 * Hello world! 
 * 
 */ 
public class App  
{ 
    public static void main( String[] args ) 
    { 
	WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/"); 
        driver.manage().window().maximize(); 
        driver.findElement(By.id("user-name")).sendKeys("standard_user"); 
        driver.findElement(By.id("password")).sendKeys("secret_sauce"); 
        driver.findElement(By.id("login-button")).click(); 
        
        driver.quit();
    } 
} 
