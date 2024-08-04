package sanjayrani.Testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import sanjayrani.resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends Basetest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result){
        test = extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        threadLocal.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        threadLocal.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch (Exception el){
            el.printStackTrace();
        }
        String path;
        try {
            path = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        threadLocal.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }

}
