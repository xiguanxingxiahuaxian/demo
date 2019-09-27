package com.example.demo.util;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class FindElement {
    public static AndroidElement findElementbyType(AndroidDriver<?>driver,String controlInof)throws Exception{
        MobileElement element=null;
        if(controlInof.startsWith("//")){
            element=(MobileElement)driver.findElementByXPath(controlInof);

           /* if(controlInof.contains("允许")||controlInof.contains("禁止")||controlInof.contains("授权")){
                element.click();
            }*/

          /*
            if (getPageSource().contains("允许") || getPageSource().contains("禁止")
                    || getPageSource().contains("授权")) {// 出现权限提示
                try {
                    FindElement.findElement()findElement(By.xpath("//android.widget.Button[contains(@text,‘允许‘)]")).click();// 点击允许
                } catch (NoSuchElementException e1) {
                    findElement(By.xpath("//android.widget.Button[contains(@text,‘授权‘)]")).click();// 点击授权
                }
            }*/


        }else if(controlInof.contains(":id/")){
            element=(MobileElement)driver.findElementById(controlInof);
        }else{
            try {
                element=(MobileElement)driver.findElementByAndroidUIAutomator("text\""+controlInof+"\")");
            }catch (Exception e){
                element=(MobileElement)driver.findElementByClassName(controlInof);
            }
        }
        return (AndroidElement)element;
    }
    public static MobileElement findElement(AndroidDriver<?>driver,String controlInfo)throws Exception{
        int elementInspectCount,elementInspcedtInterval = 0;
        elementInspectCount=Contants.elementInspectCout;
        MobileElement element=null;
        for(int i=0;i<elementInspectCount;i++){ Thread.sleep(elementInspcedtInterval);

            try {
                element=findElementbyType(driver,controlInfo);
                return  element;
            }catch (Exception e){
                continue;
            }

        }
        throw  new IllegalArgumentException("在指定的时间没有找到测试对象");
    }
}
