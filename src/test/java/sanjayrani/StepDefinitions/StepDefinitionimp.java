package sanjayrani.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import sanjayrani.Testcomponents.Basetest;
import sanjayrani.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionimp extends Basetest {

    public Landingpage landingPage;
    public Productcatalogue productCatalogue;
    public Confirmationpage confirmationPage;
    @Given("I landed on Ecommerce page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password){
        productCatalogue = landingPage.loginApplication(username,password);
    }

    @When("^I added product (.+) to cart$")
    public void i_added_product_to_cart(String productName){
        List<WebElement> products =  productCatalogue.getProductList();
        productCatalogue.addProductCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_submit_the_order(String productName) throws InterruptedException {
        Cartpage cartPage= productCatalogue.goTOCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(productName));
        Checkoutpage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.placeOrder("ind");
        confirmationPage = checkoutPage.Confirmation();
    }

    @Then("{string} message will be displayed on ConfirmationPage")
    public void message_will_be_displayed_on_confirmationpage(String string){
        Boolean message =  confirmationPage.getConfirmation().equalsIgnoreCase(string);
        Assert.assertTrue(message);
        driver.close();
    }
    @Then("{string} message is displayed")
    public void error_message_displayed(String error){
        Assert.assertEquals(error, landingPage.getErrorMessage());
        driver.close();
    }

}
