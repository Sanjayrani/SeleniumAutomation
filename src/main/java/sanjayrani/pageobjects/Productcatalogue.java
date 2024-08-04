package sanjayrani.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sanjayrani.AbstractComponents.Abstractcomponent;

import java.util.List;

public class Productcatalogue extends Abstractcomponent {

    WebDriver driver;

    public Productcatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement spinner;

    By productBy = By.cssSelector(".mb-3");
    By addCart = By.cssSelector(".card-body button:last-of-type");
    By toast = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
      waitForElementToAppear(productBy);
      return products;
    }

    public WebElement getProduct(String productName){
        WebElement item = getProductList().stream().filter(
                product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return item;
    }

    public void addProductCart(String productName){
        WebElement item = getProduct(productName);
        item.findElement(addCart).click();
        waitForElementToAppear(toast);
        waitForElementToDisappear(spinner);

    }
}
