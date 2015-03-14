package com.custom.fmc.test.controller;

import com.custom.fmc.test.automation.DemoSeleniumTesting;
import com.custom.fmc.test.automation.FMCEnvHealthCheck;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by pmoorthy on 3/9/2015.
 */
public class FMCTestController {

    public static void main(String args[]) {

        Properties props = null;
        try {
            props = new Properties();
            FileInputStream fileInputStream = new FileInputStream("C:\\DEV\\TestAutomationPOC\\conf\\FMCTestAutomation.properties");
            props.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println();
        }

        //FMC Golden Flow Testing Demo
        junit.textui.TestRunner.run(FMCEnvHealthCheck.class);

        //Selenium Demo
        junit.textui.TestRunner.run(DemoSeleniumTesting.class);
    }
}
