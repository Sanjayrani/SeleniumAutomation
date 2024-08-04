package sanjayrani.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sanjayrani.pageobjects.Cartpage;
import sanjayrani.pageobjects.Orderpage;

import java.time.Duration;

public class Abstractcomponent {

    WebDriver driver;

    public Abstractcomponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css="[routerlink*='myorders']")
    WebElement orderHeader;

    public Cartpage goTOCartPage() throws InterruptedException {
        Thread.sleep(4000);
        cartHeader.click();
        Cartpage cartpage = new Cartpage(driver);
        return cartpage;
    }

    public Orderpage goToOrderPage() throws InterruptedException {
        Thread.sleep(4000);
         orderHeader.click();
         Orderpage orderPage = new Orderpage(driver);
         return orderPage;
    }

    public void waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebelementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }
}
