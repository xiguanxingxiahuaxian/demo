package com.example.demo.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProviderFromCsv {
    // 定义csv文件的分割符
    public static char csvSeprator = ',';
    // 定义@DataProvider的name
    public static final String dataProviderName = "dataProviderName";

    public static Object[][] getCsvData(Method method, ITestContext context)
            throws IOException {
        // 获取当前文件编译后的绝对路径
        DataProviderFromCsv dataProviderFromCsv = new DataProviderFromCsv();
        String retPath = dataProviderFromCsv.getPath();

        // 获取调用方法的方法名
        String methodName = method.getName();

        // 获取调用方法的类名
        String className = null;
        String[] packageName = method.getDeclaringClass().getName().toString()
                .split("\\.");
        className = packageName[packageName.length - 1];

        // 指定当前类当前方法对应的csv文件
        String csvPath = retPath + className + "." + methodName + ".csv";

        // 从CSV文件中读取数据
        CSVReader reader = new CSVReader(new FileReader(csvPath), csvSeprator);

        // 不读第一行,第一行统一为参数的字段名字
        reader.readNext();

        // csv中每行的数据都是一个string数组
        String[] csvRow = null;
        ArrayList<Object[]> csvList = new ArrayList<Object[]>();
        // 将读取的数据，按行存入到csvList中
        while ((csvRow = reader.readNext()) != null) {
            csvList.add(csvRow);
        }

        // 定义返回值
        Object[][] results = new Object[csvList.size()][];
        // 设置二维数组每行的值，每行是个object对象
        for (int i = 0; i < csvList.size(); i++) {
            results[i] = csvList.get(i);
        }

        return results;
    }

    public  String getPath() {
        String absolutePath = null;
        try {
            absolutePath = this.getClass().getResource("").getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absolutePath;
    }
    public  static void readCsv(String path) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));

            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "GBK"), CSVParser.DEFAULT_SEPARATOR,
                    CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            String[] strs;
            while ((strs = csvReader.readNext()) != null) {
                System.out.println(Arrays.deepToString(strs));
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void writeFile(String path, String[] strArr, List<String[]> list) {
        File csv = new File(path);
        if (!csv.exists()) {
            try {
                csv.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv), "GBK"),
                    CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER);
            writer.writeNext(strArr);
            writer.writeAll(list);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   //读取CSV文件的方法
    public static Object[][]getTestData(String fileName) throws IOException {
        //定义一个集合，存csv文件中的数据
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
        file.readLine(); //跳过表头, 如果不需要表头的话，不要写这句
        while((record=file.readLine())!=null){
            String fields[] = record.split(",");
            records.add(fields);

        }
        file.close();

        //定义方法的返回值，将list转换为Object二维数据
        Object[][] results = new Object[records.size()][];
        //设置二维数每行的值，每行是一个Object对象
        for(int i=0;i<records.size();i++){
            results[i] = records.get(i);
        }

        return results;
    }
}
