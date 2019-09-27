package com.example.demo.ptest;

import com.example.demo.base.BaseVerify;
import com.example.demo.util.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class MainVerify extends BaseVerify {
    private AndroidDriver driver;

    public static boolean testResult=true;
    private Action action;
    private Method[] method;
    private String inspector;
    private String data;
    private String actionstep;
    private MobileElement mobileElement;

    @DataProvider(name = "dataProviderName",parallel =true)
    public static Object[][] getData() throws Exception{
        return  DataProviderFromCsv.getTestData(Contants.filepath+'/'+Contants.filename);
    }


    /**
     * 数据驱动
     * @throws Exception
     */
    @Test(dataProvider="dataProviderName")
    public void testApp(String username,String no) throws Exception{

        Thread.sleep(Long.parseLong("10000"));
        Action.acceptPermission(getDriver());
        loginfo.info("关闭系统弹窗");


        action=new Action(getDriver());
        method=action.getClass().getMethods();
        String filepath=Contants.filepath+'/'+Contants.xslfile;
        DataProviderFromExcel.getExcel(filepath);
        String fileshit=Contants.CSDDWJ.suit_sheet;
        int testSuiteAllNum=DataProviderFromExcel.getAllRowNum(fileshit);
        for(int testSuteNum=1;testSuteNum<=testSuiteAllNum;testSuteNum++){
            String testCaseName=DataProviderFromExcel.getCellData(Contants.CSDDWJ.suit_sheet,testSuteNum,Contants.CSDDWJ.suit_testCaseName).trim();
            String isRun=DataProviderFromExcel.getCellData(Contants.CSDDWJ.suit_sheet,testSuteNum,Contants.CSDDWJ.suit_isRun).trim();
            String testCaseDetail=DataProviderFromExcel.getCellData(Contants.CSDDWJ.suit_sheet,testSuteNum,Contants.CSDDWJ.suit_testCaseDetail).trim();
            if(isRun.equals("√")){
                loginfo.info("运行测试用例"+testCaseName+"测试用例详细描述");
                testResult=true;
                int testCaseAllNum=DataProviderFromExcel.getAllRowNum(testCaseName);
                loginfo.info("测试步骤"+testCaseAllNum);
                for(int testCaseNum=1;testCaseNum<=testCaseAllNum;testCaseNum++){
                    inspector=DataProviderFromExcel.getCellData(testCaseName,testCaseNum,Contants.CSYL.Col_inspeor).trim();
                    data=DataProviderFromExcel.getCellData(testCaseName,testCaseNum,Contants.CSYL.Col_data);
                    actionstep=DataProviderFromExcel.getCellData(testCaseName,testCaseNum,Contants.CSYL.Col_actionStep);
                    if(!inspector.isEmpty()){
                         mobileElement = FindElement.findElement(getDriver(), inspector);
                    }
                    execute_actions(testCaseNum,testCaseName);
                    if(testResult==false){
                        loginfo.info("测试用例的执行结果是false");
                        DataProviderFromExcel.SetCellData(testCaseNum,Contants.CSDDWJ.suit_result,true,testCaseName,Contants.filepath+'/'+Contants.xslfile);
                    }else{
                        loginfo.info("测试用例的执行结果是true");
                        DataProviderFromExcel.SetCellData(testCaseNum,Contants.CSDDWJ.suit_result,true,testCaseName,Contants.filepath+'/'+Contants.xslfile);
                    }
                }
            }
        }
    }
    public void execute_actions(int testCaseNum,String testCaseName)throws Exception{
        try {
            for(int i=0;i<method.length;i++){
                if(method[i].getName().equals(actionstep)){
                    method[i].invoke(action,mobileElement,data);
                    if(testResult==true){
                        loginfo.info("测试步骤执行结果为true");
                        DataProviderFromExcel.SetCellData(testCaseNum,Contants.CSYL.Col_result,true,testCaseName,Contants.filepath+'/'+Contants.xslfile);
                        break;
                    }else {
                        loginfo.info("测试步骤执行结果为false");
                        DataProviderFromExcel.SetCellData(testCaseNum,Contants.CSYL.Col_result,true,testCaseName,Contants.filepath+'/'+Contants.xslfile);
                        break;
                    }
                }
            }
        }catch (Exception e){
                e.printStackTrace();
        }
    }
    @Test
    public void  testApp(){

    }


}
