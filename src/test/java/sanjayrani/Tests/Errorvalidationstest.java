package sanjayrani.Tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import sanjayrani.Testcomponents.Basetest;
import sanjayrani.Testcomponents.Retry;
import sanjayrani.pageobjects.Cartpage;
import sanjayrani.pageobjects.Checkoutpage;
import sanjayrani.pageobjects.Confirmationpage;
import sanjayrani.pageobjects.Productcatalogue;

import java.time.Duration;
import java.util.List;

public class Errorvalidationstest extends Basetest {

    @Test(retryAnalyzer = Retry.class)

    public void errorValidations() {
        landingPage.loginApplication("jack.davis1602@gmail.com", "Jack@1234");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        landingPage.getErrorMessage();
    }

    @Test
    public void productOrder() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String productName = "ZARA COAT 3";

        Productcatalogue productCatalogue = landingPage.loginApplication("jack.davis1602@gmail.com", "Jack@123");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductCart(productName);
        Cartpage cartPage = productCatalogue.goTOCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(productName));
        Checkoutpage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.placeOrder("ind");
        Confirmationpage confirmationPage = checkoutPage.Confirmation();
        Boolean message = confirmationPage.getConfirmation().equalsIgnoreCase("Thankyou for the order.");

        Assert.assertTrue(message);
    }
}
