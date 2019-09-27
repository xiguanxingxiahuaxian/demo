package com.example.demo.testCase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class login {

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        File file =new File("D:\\workofstudy\\demo\\apps\\app-debug.apk");
        capabilities.setCapability("deviceName", "127.0.0.1:62001");
        capabilities.setCapability("app",file.getAbsolutePath());
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        capabilities.setCapability("appPackage", "com.neuqsoft.ggfwandroid_hd_medical");
        capabilities.setCapability("appActivity", "com.neuqsoft.ggfwandroid.mvp.ui.activity.WelcomeGuideActivity");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, capabilities);
    }

    @Test
    public void sampleTest() {

    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}

