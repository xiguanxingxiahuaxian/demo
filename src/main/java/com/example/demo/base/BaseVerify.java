package com.example.demo.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URL;

/**
 * 1 加入logger4j
 * 2 建立基础类 该基础类连接本地手机
 */
public class BaseVerify {

    public AndroidDriver driver;
    public Logger loginfo;

    @BeforeClass
    public void setUp() throws  Exception{

        loginfo=Logger.getLogger(BaseVerify.class.getName());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File file =new File("D:\\workofstudy\\demo\\apps\\app-debug.apk");

        capabilities.setCapability("deviceName", "45UNW19430002551");
        capabilities.setCapability("app",file.getAbsolutePath());
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "com.neuqsoft.ggfwandroid_hd_medical");
        capabilities.setCapability("appActivity", "com.neuqsoft.ggfwandroid.mvp.ui.activity.SplashActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities );

    }
    //获取驱动

    @AfterClass
    public void quite() throws  Exception{

       // driver.quit();
    }
    public  AndroidDriver<AndroidElement>getDriver(){
        return driver;
    }

}
