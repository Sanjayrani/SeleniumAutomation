package sanjayrani.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sanjayrani.AbstractComponents.Abstractcomponent;

public class Checkoutpage extends Abstractcomponent {

    WebDriver  driver;

    public Checkoutpage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement button;

    @FindBy(css=".action__submit")
    WebElement submit;


    By placeOrder = By.cssSelector(".ta-results");
    By orderSuccess = By.cssSelector(".action__submit");



    public void placeOrder(String name){
        country.sendKeys(name);
        waitForElementToAppear(placeOrder);
        button.click();
        waitForElementToAppear(orderSuccess);
    }

    public Confirmationpage Confirmation(){
        submit.click();
        Confirmationpage confirmationPage = new Confirmationpage(driver);
        return confirmationPage;
    }




}
