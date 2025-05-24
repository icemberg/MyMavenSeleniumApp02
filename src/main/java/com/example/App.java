
package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException {
        // Setup Chrome options
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();

        // Create isolated temp user profile
        Path tempProfile = Files.createTempDirectory("chrome-profile-" + System.nanoTime());
        System.out.println("Using temp profile: " + tempProfile.toAbsolutePath());
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());

        // Optional for CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(5000); // Pause to observe behavior
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();

            // Clean up temp directory
            try (Stream<Path> walk = Files.walk(tempProfile)) {
                walk.sorted((a, b) -> b.compareTo(a)) // delete children first
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.err.println("Failed to delete " + path + ": " + e.getMessage());
                        }
                    });
            }
        }
    }
}

