package com.example.demo.util;


import com.example.demo.base.BaseVerify;
import com.example.demo.ptest.MainVerify;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Action {
    private final AndroidDriver<AndroidElement> driver;
    private final Logger info;

    public Action(AndroidDriver<AndroidElement>driver) {
        this.driver=driver;
        info=Logger.getLogger(Action.class.getName());
    }

    /**
     * 持续点击控件
     *
     * @param driver
     * @param by
     */
    public static void keepClickElement(AppiumDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            while (true) {
                if (element.isDisplayed()) {
                    element.click();
                } else {
                    break;
                }
            }
        } catch (NoSuchElementException e) {

        }
    }

    /**
     * 出现阻塞步骤的系统弹窗时，accept 继续
     *
     * @param driver
     */
    public static void acceptPermission(AppiumDriver driver) {
        keepClickElement(driver, new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*允许.*\")"));
    }
    /**
     * 单击
     * @param mobileElement
     * @param data
     */
     public void click(MobileElement mobileElement,String data){


        try {
            mobileElement.click();
        }catch (Exception e){
            try {
                Thread.sleep(Contants.elementInspectInterval);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                MainVerify.testResult=false;
            }
            mobileElement.click();
        }
    }

    /**
     * 针对单元框进行单击
     * @param mobileElement
     * @param data
     */
    public void click_raido(MobileElement mobileElement,String data){
        try {
            if(data.toLowerCase().equals("yes")){
                if(!mobileElement.isSelected()){
                    mobileElement.click();
                }
            }
        }catch (Exception e){
            MainVerify.testResult=false;
            e.printStackTrace();
        }
    }

    /**
     * 对编辑框输入指定得数据
     * @param mobileElement
     * @param data
     */
    public void input(MobileElement mobileElement,String data){
        try {
            this.click(mobileElement,data);
            mobileElement.clear();
            mobileElement.sendKeys(data);
        }catch (Exception e){
            MainVerify.testResult=false;
            e.printStackTrace();
        }
    }

    /**
     * 后退操作
     * @param mobileElement
     * @param data
     */
    public void back(MobileElement mobileElement,String data){
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    /**
     * 验证操作
     * @param mobileElement
     * @param data
     */
    public void verify(MobileElement mobileElement,String data)
    {
        try {
            String actualResult = mobileElement.getAttribute("text");
            if(!actualResult.equals(data)){
                MainVerify.testResult=false;
            }
        }catch (Exception e){
            MainVerify.testResult=false;
        }

    }
    public void waitForLoadingActivity(MobileElement mobileElement,String data) throws  InterruptedException{
        Thread.sleep(3000);
        info.info(driver.currentActivity());
         int  activityInspectCout=Contants.activityInspectCout;
        int  activityInspectInterval=Contants.activityInspectInterval;
        int i=0;
        Thread.sleep(activityInspectInterval);
        while (i<activityInspectCout){
            try {
                if(data.contains(driver.currentActivity())){
                    info.info(data+"出现");
                    break;
                }else{
                    info.info(data+"未出现!");
                    Thread.sleep(activityInspectInterval);
                    i++;
                }
            }catch (Exception e){
                i++;
                info.info("尝试"+activityInspectCout+"次"+data+"未出现");
                MainVerify.testResult=false;
            }
        }
    }
}

