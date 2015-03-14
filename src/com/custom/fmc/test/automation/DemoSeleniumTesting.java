package com.custom.fmc.test.automation;
import static org.junit.Assert.assertEquals;
import junit.framework.*;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class DemoSeleniumTesting extends TestCase {

    private static ChromeDriverService service;
    private WebDriver driver;
    private EventFiringWebDriver driverWithReporting;
    private static String mainWindow;
    private static String expectedScenario1 = "webdriver - Google Search";
    private String expectedScenario2 = "Hi padmara... !";
    private String expectedScenario3 = "DeloittePeopleNetwork";

/*    @BeforeClass
    public static void createAndStartService() {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/DEV/TestAutomationPOC/lib/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @BeforeTest
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }*/

    @Test
    public void testGoogleSearch() throws IOException{

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/DEV/TestAutomationPOC/lib/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());

        driver.get("http://www.google.co.in");
        //mainWindow = driver.getWindowHandle();
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("webdriver");
        //driver.switchTo().window(mainWindow);
        driver.findElement(By.xpath("//*[@id='sblsbb']")).click();//*[@id="sblsbb"]/button
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String temp = driver.getTitle();
        System.out.println("Actual result : " + driver.getTitle());
        System.out.println("Expected result : webdriver - Google Search");

        assertEquals(expectedScenario1, temp);
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/" + new Exception().getStackTrace()[0].getMethodName() + "/Passed.jpg"));

        driver.quit();
        service.stop();
    }

    @Test
    public void testFlipkartLogin() throws InterruptedException,IOException {

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/DEV/TestAutomationPOC/lib/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());

        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);
        driver.get("http://www.flipkart.com/");
        driver.manage().window().maximize();
        mainWindow = driver.getWindowHandle();

        //*[@id="fk-mainhead-id"]/div[1]/div/div[2]/div[1]/ul/li[8]/a - Login Link
        driver.findElement(By.xpath("//*[@id=\"fk-mainhead-id\"]/div[1]/div/div[2]/div[1]/ul/li[8]/a")).click();
        //*[@id="login_email_id"]
        //#login_email_id
        driver.findElement(By.id("login_email_id")).sendKeys("@gmail.com");
        //*[@id="login_email_id1"]
        //driver.findElement(By.cssSelector("#login_email_id1")).sendKeys("padmaraj.moorthy@gmail.com");

        //*[@id="login_password"]
        //*[@id="login_password1"]
        driver.findElement(By.id("login_password")).sendKeys("");
        //*[@id="login-form"]/div[3]/div[2]/input[2]
        //*[@id="login-cta"]
        driver.findElement(By.xpath("//*[@id='login-form']/div[3]/div[2]/input[2]")).click();
        //driver.findElement(By.xpath("//*[@id=\"login-cta\"]")).click();
        //If there is an alert use this
        //driver.switchTo().window(mainWindow);
        String welcomeText = driver.findElement(By.cssSelector("#fk-mainhead-id > div.header-topbar > div > div.unitExt.mainUnit > div.header-links.unitExt > ul > li.no-border.greeting-link > a")).getText();
        System.out.println(welcomeText);
        assertEquals(expectedScenario2, welcomeText);
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/" + new Exception().getStackTrace()[0].getMethodName() + "/Passed.jpg"));

        driver.quit();
        service.stop();
    }
    @Test
    public void testDeloitteSite() throws IOException{

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/DEV/TestAutomationPOC/lib/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());

        driver.manage().window().maximize();
        driver.get("https://deloittenet.deloitte.com/Pages/Home.aspx");
        driver.findElement(By.xpath("//*[@id=\"s4-bodyContainer\"]/header/div/nav/div[1]/a/img")).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/"+new Exception().getStackTrace()[0].getMethodName()+"/Passed.jpg"));

        driver.switchTo().window(tabs.get(1));
        System.out.println("DPN page :"+driver.getTitle());
        assertEquals("DeloittePeopleNetwork", driver.getTitle());


        driver.quit();
        service.stop();
    }

}