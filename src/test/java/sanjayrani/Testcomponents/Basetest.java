package sanjayrani.Testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sanjayrani.pageobjects.Landingpage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Basetest {
    public WebDriver driver;
    public Landingpage landingPage;

    public WebDriver intiliazeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/sanjayrani/resources/Globaldata.properties");
        prop.load(file);

        String browserName =  System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");

        if(browserName.contains("chrome")){
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")){
            options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,1300));
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webDriver.gecko.driver", System.getProperty("user.dir")+"src/test/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("/usr/bin/firefox");
            WebDriver driver = new FirefoxDriver(options);
//            driver = new FirefoxDriver();
            //
        }

        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1440,1300));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public Landingpage launchApplication() throws IOException {
        driver = intiliazeDriver();
        landingPage = new Landingpage(driver);
        landingPage.goTo();
        return landingPage;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+ testCaseName+".png";
    }

        public List<HashMap<String, String>> getJsonDataToHmap(String filePath) throws IOException {

            String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            List<HashMap<String,String>> map = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
            });
            return map;
        }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
