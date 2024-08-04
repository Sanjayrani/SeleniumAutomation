package sanjayrani.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sanjayrani.AbstractComponents.Abstractcomponent;

import java.util.List;

public class Cartpage extends Abstractcomponent {

    WebDriver driver;

    public Cartpage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".cartSection h3")
    private List<WebElement> cartItems;

    @FindBy(css=".totalRow button")
    WebElement checkoutele;

    public Boolean verifyProductDisplay(String productName){
        Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
        return match;
    }

    public Checkoutpage goToCheckOut(){
        checkoutele.click();
        Checkoutpage checkoutPage = new Checkoutpage(driver);
        return checkoutPage;
    }
}
