package sanjayrani.Tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sanjayrani.pageobjects.Landingpage;

import java.time.Duration;
import java.util.List;

public class Standalonetest {

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String productName = "ZARA COAT 3";
        driver.get("https://rahulshettyacademy.com/client");
        Landingpage loading = new Landingpage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("jack.davis1602@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Jack@123");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement item = products.stream().filter(
                product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        if(item!=null)
            item.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,800)");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
        driver.findElement(By.cssSelector(".action__submit")).click();

        Boolean message = driver.findElement(By.className("hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
        Assert.assertTrue(message);

        driver.close();


    }
}
