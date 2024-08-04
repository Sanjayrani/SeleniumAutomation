package sanjayrani.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sanjayrani.AbstractComponents.Abstractcomponent;

import java.util.List;

public class Orderpage extends Abstractcomponent {

    WebDriver driver;

    public Orderpage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tr th:nth-child(3)")
    List<WebElement> orderItems;


    public Boolean verifyOrderDisplay(String productName){
//        waitForWebelementToAppear(orderItems);
        Boolean match = orderItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
        return match;
    }

}
