package com.example.demo.ptest;

import com.example.demo.util.Contants;
import com.example.demo.util.DataProviderFromCsv;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginVerify {

    public LoginVerify() {

    }





   /* private AndroidDriver driver;

    @BeforeTest
    public void loginVerify() throws Exception{

        *//**
         *
         *   "deviceName": "127.0.0.1:62001",
         *   "appPackage": "com.example.myapplication",
         *   "appActivity": "com.example.myapplication.MainActivity",
         *   "platformName": "android",
         *   "platformVersion": "5.1.1"
         *
         *//*




        DesiredCapabilities capabilities = new DesiredCapabilities();
        File  file =new File("D:\\workofstudy\\demo\\apps\\app-debug.apk");


        capabilities.setCapability("deviceName", "45UNW19430002551");
        capabilities.setCapability("app",file.getAbsolutePath());
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "com.neuqsoft.ggfwandroid_hd_medical");
        capabilities.setCapability("appActivity", "com.neuqsoft.ggfwandroid.mvp.ui.activity.SplashActivity");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities );
       *//* driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities );
*//*
        *//**
         * 测试用户
         *//*
    }
    @Test
    public void testApp() throws Exception{


  *//*      PointOption pointOption= PointOption.point(788, 2195);
        pointOption.build().put(788, 2195);
        pointOption.build().put(788, 2195);
        pointOption.build().put(708, 181);
        pointOption.build().put(530, 1050);
        pointOption.build().put(535, 1350);
        pointOption.build().put(545, 981);
*//*

        //测试登陆
//        (new TouchAction(driver)).moveTo(pointOption).perform().release();
       *//* (new TouchAction(driver)).tap(pointOption).perform();
        (new TouchAction(driver)).tap(pointOption).perform();
        MobileElement el1 = (MobileElement) driver.findElementById("com.neuqsoft.ggfwandroid_hd_medical:id/et_login_user");
        el1.sendKeys("130403198812300612");
        MobileElement el2 = (MobileElement) driver.findElementById("com.neuqsoft.ggfwandroid_hd_medical:id/et_login_password");
        el2.sendKeys("o12345678");
        (new TouchAction(driver)).tap(pointOption).perform();
        (new TouchAction(driver)).tap(pointOption).perform();
        MobileElement el3 = (MobileElement) driver.findElementById("com.neuqsoft.ggfwandroid_hd_medical:id/et_login_password");
        el3.sendKeys("o1234567");
        (new TouchAction(driver)).tap(pointOption).perform();*//*

    }*/

}
