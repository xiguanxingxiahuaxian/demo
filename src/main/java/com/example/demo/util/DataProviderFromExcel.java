package com.example.demo.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProviderFromExcel {

    private static File file;
    private static XSSFWorkbook wofk;
    private static XSSFSheet sheet;
    private static XSSFCell cell;
    private static String strCell;

    /**
     * 初始化文档，设置excel的操作路径
     * @param filePath
     * @throws Exception
     */
    public static void getExcel(String filePath) throws Exception{
        file =new File(filePath);
        FileInputStream fileInputStream =new FileInputStream(file);
        wofk=new XSSFWorkbook(fileInputStream);
        if(null==wofk){
            throw new Exception("创建Excel工作博为空！");
        }
     }

    /**
     * 读取指定单元格的值
     * @param sheetName
     * @param rowNum
     * @param colNum
     * @return
     * @throws Exception
     */
     public static String getCellData(String sheetName,int rowNum,int colNum)throws Exception{
        sheet=wofk.getSheet(sheetName);
        try {
            cell=sheet.getRow(rowNum).getCell(colNum);
            String cellData=getCellValue(cell);
            return cellData;
        }catch (Exception e){
            return "";
        }
     }

    /**
     * 根据单元格的不同来读取值得不同
     * @param cell
     * @return
     */
      private static  String getCellValue(XSSFCell cell){
        if(cell.getCellTypeEnum()== CellType.STRING){
            strCell=cell.getStringCellValue();
        }else if(cell.getCellTypeEnum()==CellType.NUMERIC){
            strCell=String.valueOf(cell.getNumericCellValue());
            strCell=strCell.split(".")[0];
        }else if(cell.getCellTypeEnum()==CellType.BOOLEAN){
            strCell=String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellTypeEnum()==CellType.BLANK){
            strCell="";
        }else{
            strCell="";
        }
        return strCell;
      }

    /**
     * 向指定得单元格写入数据
     * @param rowNum
     * @param colNum
     * @param result
     * @param sheetName
     * @param filePah
     * @throws Exception
     */
      public static void SetCellData(int rowNum,int colNum,boolean result,String sheetName,String filePah)throws Exception{
          sheet=wofk.getSheet(sheetName);
          try {
              XSSFRow row = sheet.getRow(rowNum);
              cell=row.getCell(colNum);
              if(cell==null){
                  cell=row.createCell(colNum);
                  cell.setCellValue(result);
              }else{
                  cell.setCellValue(result);
              }
              FileOutputStream outputStream =new FileOutputStream(filePah);
              wofk.write(outputStream);
              outputStream.close();

          }catch (Exception e){
              throw e;
          }
      }

    /**
     * 获取指定sheet页得单元格行数
     * @param sheetName
     * @return
     */
      public static int getAllRowNum(String sheetName){
          sheet=wofk.getSheet(sheetName);
          return sheet.getLastRowNum();
      }

}
