package sanjayrani.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sanjayrani.AbstractComponents.Abstractcomponent;

public class Confirmationpage extends Abstractcomponent {

    WebDriver driver;

    public Confirmationpage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "hero-primary")
    WebElement orderPlaced;

    public String getConfirmation(){
        String message = orderPlaced.getText();
        return message;
    }
}

