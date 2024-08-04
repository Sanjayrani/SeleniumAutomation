package sanjayrani.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sanjayrani.AbstractComponents.Abstractcomponent;

public class Landingpage extends Abstractcomponent {
    WebDriver driver;

    public Landingpage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement login;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorText;

    public Productcatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();
        Productcatalogue productCatalogue = new Productcatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage(){
        waitForWebelementToAppear(errorText);
        return errorText.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


}
