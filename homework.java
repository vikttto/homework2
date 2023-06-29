package org.prog.selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homework {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();

            driver.get("https://rozetka.com.ua/ua/");



            WebElement searchBar = driver.findElement(By.name("search"));
            searchBar.sendKeys("IPhone");
            searchBar.sendKeys(Keys.ENTER);

            List<WebElement> searchResults = new WebDriverWait(driver, Duration.ofSeconds(5L))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText("IPhone")));

            if (searchResults.isEmpty()) {
                throw new RuntimeException("No search results were found!");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
