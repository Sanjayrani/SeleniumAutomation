package sanjayrani.Tests;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sanjayrani.Testcomponents.Basetest;
import sanjayrani.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Submitformtest extends Basetest {
    String productName = "ZARA COAT 3";
  @Test(dataProvider ="getData", groups = {"purchase"})
          public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Productcatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement> products =  productCatalogue.getProductList();
        productCatalogue.addProductCart(input.get("productName"));
        Cartpage cartPage= productCatalogue.goTOCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(input.get("productName")));
        Checkoutpage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.placeOrder("ind");
        Confirmationpage confirmationPage = checkoutPage.Confirmation();
        Boolean message =  confirmationPage.getConfirmation().equalsIgnoreCase("Thankyou for the order.");

        Assert.assertTrue(message);


//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        js.executeScript("window.scrollBy(0,800)");
    }

//    @Test(dependsOnMethods = {"submitOrder"})
//    public void orderPlaced() throws InterruptedException {
//        Productcatalogue productCatalogue = landingPage.loginApplication("jack.davis1602@gmail.com","Jack@123");
//        Orderpage orderPage = productCatalogue.goToOrderPage();
//        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
//
//    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<String,String>();
//        map.put("email","jack.davis1602@gmail.com");
//        map.put("password","Jack@123");
//        map.put("productName","ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<String,String>();
//        map1.put("email","shetty@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("productName","ADIDAS ORIGINAL");

        List<HashMap<String, String>> data = getJsonDataToHmap(System.getProperty("user.dir")+"/src/test/java/sanjayrani/Data/PurchaseOrder.json");

        return new Object[][]{{data.get(0)}, {data.get(1)}};

    }

//    public String getScreenshot(String testCaseName) throws IOException {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
//        FileUtils.copyFile(source,file);
//        return System.getProperty("user.dir")+"//reports//"+ testCaseName+".png";
//    }

//    @DataProvider
//    public Object[][] getData(){
//      return new Object[][]{{"email","password","name"},{"email","password","stem.getProperty("user.dir")+"/src/test/java/sanjayrani/Data/PurchaseOrder.json"name"}};
//    }
}
