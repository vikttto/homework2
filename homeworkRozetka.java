package HomeworkRozetka;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class homeworkRozetka {
    private WebDriver driver;

    @BeforeMethod
    public void loadGoogle() {
        WebDriverNG.driver.get("https://google.com/");
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void testAddToCart() {

        driver.get("https://www.rozetka.com.ua/");


        WebElement searchField = driver.findElement(By.cssSelector("input[name='search']"));
        searchField.sendKeys("айфон");
        searchField.sendKeys(Keys.ENTER);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".goods-tile")));


        WebElement firstProduct = driver.findElement(By.cssSelector(".goods-tile__heading a"));
        String productName = firstProduct.getText();
        String productPrice = driver.findElement(By.cssSelector(".goods-tile__price-value")).getText();
        firstProduct.click();


        WebElement addToCartButton = driver.findElement(By.cssSelector(".buy-button"));
        addToCartButton.click();


        WebElement cartLink = driver.findElement(By.cssSelector(".header-actions__button_type_cart"));
        cartLink.click();


        String cartProductName = driver.findElement(By.cssSelector(".cart-product__title a")).getText();
        String cartProductPrice = driver.findElement(By.cssSelector(".cart-product__price")).getText();

        Assert.assertEquals(cartProductName, productName, "Назва товару в кошику не співпадає з вибраним товаром");
        Assert.assertEquals(cartProductPrice, productPrice, "Ціна товару в кошику не співпадає з вибраним товаром");
    }
}
