package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        // Setup Chrome options
        ChromeOptions options = new ChromeOptions();

        // Use a unique user data directory to avoid "already in use" error
	Path tempProfile = Files.createTempDirectory("chrome-profile-" + System.nanoTime());
	System.out.println("Using temp profile: " + tempProfile.toAbsolutePath());
	options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());

	System.out.println("DISPLAY env: " + System.getenv("DISPLAY"));

        // Optional: Launch in full screen
        options.addArguments("--start-maximized");

        // Create ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);

        // Your existing logic
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Wait so you can see the result (if running with GUI)
        try {
            Thread.sleep(5000); // wait 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}

