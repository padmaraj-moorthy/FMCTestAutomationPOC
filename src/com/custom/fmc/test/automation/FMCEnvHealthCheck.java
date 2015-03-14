package com.custom.fmc.test.automation;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by pmoorthy on 3/11/2015.
 */
@RunWith(JUnit4.class)
public class FMCEnvHealthCheck extends TestCase {

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
        System.out.println("Properties Loaded!" + props.getProperty("testFMCAppExpected"));
        fileInputStream.close();
    }

    @Test
    public void testOpenTextLogin() throws InterruptedException,IOException {


        try {
            loadProps();
        } catch (Exception e) {
            e.printStackTrace();
        }

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(service.getUrl(),DesiredCapabilities.chrome());
        //HtmlUnitDriver driver = new HtmlUnitDriver();
        //driver.setJavascriptEnabled(true);

        driver.manage().window().maximize();
        driver.get(props.getProperty("testOpenTextLogin_URL"));
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(props.getProperty("testOpenTextLogin_IP_1_Username"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(props.getProperty("testOpenTextLogin_IP_1_Password"));
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        assertEquals(props.getProperty("testOpenTextLogin_OP"), driver.getTitle());

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/"+new Exception().getStackTrace()[0].getMethodName()+"/Passed.jpg"));

        driver.quit();
        service.stop();
    }

    @Test
    public void testFMCApp() throws IOException, InterruptedException {

        try {
            loadProps();
        } catch (Exception e) {
            e.printStackTrace();
        }

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
        driver.get(props.getProperty("testFMCApp_URL"));
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Username"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(props.getProperty("testOpenTextDirectoryServices_IP_1_Password"));
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
        Thread.sleep(1000);

        System.out.println("Expected result : " + props.getProperty("testFMCApp_OP_1"));
        System.out.println("Actual result : " + driver.getTitle());
        assertEquals(props.getProperty("testFMCApp_OP_1"), driver.getTitle());

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:/DEV/TestAutomationPOC/Screenshots/"+new Exception().getStackTrace()[0].getMethodName()+"/Passed.jpg"));

        driver.quit();
        service.stop();
    }

    @Test
    public void testGoogle() throws IOException, InterruptedException {

        try {
            loadProps();
        } catch (Exception e) {
            e.printStackTrace();
        }

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(props.getProperty("chromeDriverLoc")))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
        driver.get(props.getProperty("testGoogle_URL"));

        driver.close();
        service.stop();
    }


    @Test
    public void testFMCDBConnection() {

        try {
            loadProps();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection conn = null;

        try {

            String dbURL = props.getProperty("testFMCDBConnection_dbURL");
            String user = props.getProperty("testFMCDBConnection_dbUsername");
            String pass = props.getProperty("testFMCDBConnection_dbPassword");
            conn = DriverManager.getConnection(dbURL, user, pass);

            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                ResultSet rs = dm.getTables(null, null, "%", null);
                System.out.println("No of Tables"+ rs.getFetchSize());
                while(rs.next()){
                    System.out.println("Table :"+rs.getString(3));
                }
                assertEquals(props.getProperty("testFMCDBConnection_Tables"),String.valueOf(rs.getFetchSize()));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
