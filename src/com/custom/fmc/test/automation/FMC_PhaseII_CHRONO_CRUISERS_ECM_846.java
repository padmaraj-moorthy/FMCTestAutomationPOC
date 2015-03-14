package com.custom.fmc.test.automation;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.xpath.SourceTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by pmoorthy on 3/11/2015.
 */
@RunWith(JUnit4.class)
public class FMC_PhaseII_CHRONO_CRUISERS_ECM_846 extends TestCase {

    private static ChromeDriverService service;
    private WebDriver driver;
    private EventFiringWebDriver driverWithReporting;
    private static String mainWindow;
    private static Properties props;

    public static void loadProps() throws Exception {
        props = new Properties();
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("C:\\DEV\\TestAutomationPOC\\conf\\FMCTestAutomation.properties");
        props.load(fileInputStream);
       // System.out.println("Properties Loaded!" + props.getProperty("testFMCAppExpected"));
        fileInputStream.close();
    }

    public static void HighlightMyElement(WebElement element, WebDriver driver) {

        for (int i = 0; i < 2; i++)
        {
            JavascriptExecutor javascript = (JavascriptExecutor) driver;
            //javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: orange; border: 4px solid orange;");
            javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: green; border: 4px solid pink;");
            //javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 4px solid yellow;");
            //javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    @Test
    public void testOpenTextDirectoryServices() throws InterruptedException,IOException,Exception {
        loadProps();
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        //HtmlUnitDriver driver = new HtmlUnitDriver();
        //driver.setJavascriptEnabled(true);

        driver.manage().window().maximize();
        driver.get(props.getProperty("testOpenTextDirectoryServices_URL"));
        System.out.println("Op1"+props.getProperty("testOpenTextDirectoryServices_OP_1"));
        //assertEquals(props.getProperty("testOpenTextDirectoryServices_OP_1"),driver.getTitle());
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Username"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Password"));
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        //assertEquals(props.getProperty("testOpenTextDirectoryServices_OP_2"),driver.getTitle());
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"globalMenu_2\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"globalMenuItem_2.3\"]")).click();
        assertEquals(props.getProperty("testOpenTextDirectoryServices_OP_3"),driver.getTitle());
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/" + new Exception().getStackTrace()[0].getMethodName() + "/Passed.jpg"));
        driver.quit();
        service.stop();
    }

    @Test
    public void testPageOnLoad() throws InterruptedException,IOException,Exception {
        loadProps();
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
        driver.get(props.getProperty("testOpenTextDirectoryServices_URL"));
        System.out.println("Op1"+props.getProperty("testOpenTextDirectoryServices_OP_1"));
        //assertEquals(props.getProperty("testOpenTextDirectoryServices_OP_1"),driver.getTitle());
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Username"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Password"));
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rowCell1\"]/td[3]/a[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rowCell2\"]/td[3]/a[1]")).click();
        Thread.sleep(1000);
        assertEquals(props.getProperty("testPageOnLoad_OP"),driver.getTitle());
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/" + new Exception().getStackTrace()[0].getMethodName() + "/Passed.jpg"));
        driver.quit();
        service.stop();
    }

    @Test
    public void testCreateNewClient() throws InterruptedException,IOException,Exception {
        loadProps();
        boolean testCreateNewClient = false;
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
        driver.get(props.getProperty("testOpenTextDirectoryServices_URL"));
        System.out.println("Op1"+props.getProperty("testOpenTextDirectoryServices_OP_1"));
        //assertEquals(props.getProperty("testOpenTextDirectoryServices_OP_1"),driver.getTitle());
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Username"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Password"));
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rowCell1\"]/td[3]/a[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rowCell2\"]/td[3]/a[1]")).click();
        Thread.sleep(1000);
        String clientNumber = props.getProperty("testCreateNewClient_IP_ClientNumber")+new Date().getTime();
        String clientName = props.getProperty("testCreateNewClient_IP_ClientName")+new Date();
        System.out.println("Client Number :"+clientNumber);
        System.out.println("Client Name :"+clientName);
        driver.findElement(By.xpath("//*[@id=\"ccn\"]")).sendKeys(clientNumber);
        driver.findElement(By.xpath("//*[@id=\"fcn\"]")).sendKeys(clientName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
        Thread.sleep(6000);
        HighlightMyElement(driver.findElement(By.xpath("//*[@id=\"Successmsg\"]")), driver);
        Thread.sleep(3000);
        if(driver.findElement(By.xpath("//*[@id=\"Successmsg\"]")).getText().contains(clientNumber)){
            testCreateNewClient = true;
        }
        /*
        WebElement table = driver.findElement(By.id("listClientNames"));
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                //System.out.println(cell.getText());
                if(cell.getText().contains(clientNumber)){
                    testCreateNewClient = true;
                }
            }
        }
        */
        Thread.sleep(5000);
        assertEquals(testCreateNewClient,true);
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/" + new Exception().getStackTrace()[0].getMethodName() + "/Passed.jpg"));
        driver.quit();
        service.stop();
    }

    @Test
    public void testSearchClient() throws InterruptedException,IOException,Exception {
        loadProps();
        boolean testSearchClient = false;
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
        driver.get(props.getProperty("testOpenTextDirectoryServices_URL"));
        System.out.println("Op1"+props.getProperty("testOpenTextDirectoryServices_OP_1"));
        //assertEquals(props.getProperty("testOpenTextDirectoryServices_OP_1"),driver.getTitle());
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Username"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Password"));
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rowCell1\"]/td[3]/a[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rowCell2\"]/td[3]/a[1]")).click();
        Thread.sleep(1000);
        System.out.println("Client name :" + props.getProperty("testSearchClient_IP_ClientName"));
        driver.findElement(By.xpath("//*[@id=\"clientName\"]")).sendKeys(props.getProperty("testSearchClient_IP_ClientName"));
        Thread.sleep(2000);
        System.out.println("Results :"+driver.findElement(By.id("TotalCount")).getAttribute("value"));

        WebElement table = driver.findElement(By.id("listClientNames"));
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.println(cell.getText());
                if(cell.getText().contains(props.getProperty("testSearchClient_IP_ClientName"))){

                    testSearchClient = true;
                }
            }
        }
        Thread.sleep(2000);
        assertEquals(true,testSearchClient);
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/" + new Exception().getStackTrace()[0].getMethodName() + "/Passed.jpg"));

        driver.quit();
        service.stop();
    }
}
