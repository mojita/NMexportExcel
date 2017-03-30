package com.sobey.exportExcel.util;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.sobey.exportExcel.model.ExcelModel;
import com.sobey.exportExcel.model.MetaData;

/**
 * Created by lijunhong on 17/3/30.
 */
public class ExcelFileUtil {

    private static Logger logger = Logger.getLogger(ExcelFileUtil.class);
    public static String filePath;          //这里是获取到jar所在的文件路径:文件路径后面带有"/"
    public static String fileName = "";     //记录了根据model匹配的文件名
    public static Boolean isWindosSystem = false;

    static {

        //获取当前运行的系统名,判断是在什么系统下运行
        Properties properties = System.getProperties();
        String name = properties.getProperty("os.name");
        System.out.println(name);

        String allPath = ExcelFileUtil.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        String path = null;


        if(name.startsWith("win") || name.startsWith("Win")){
            //windows操作系统  file:/c:/test/test.jar!/BOOT/..
            int lastIndexOf = allPath.lastIndexOf(".jar")+4;
            String jarPath = allPath.substring(0,lastIndexOf);
            int pathIndexOf = jarPath.lastIndexOf("/")+1;
            int startFileIndexOf = jarPath.indexOf("/")+1;
            path = jarPath.substring(startFileIndexOf,pathIndexOf);
            isWindosSystem = true;
        }else {
            //mac os x file:/Users/libai/test.jar!/BOOT/..
            int lastIndexOf = allPath.lastIndexOf(".jar")+4;
            String jarPath = allPath.substring(0,lastIndexOf);
            int pathIndexOf = jarPath.lastIndexOf(File.separator)+1;
            int startFileIndexOf = jarPath.indexOf(":")+1;
            if(startFileIndexOf>0) {
                path = jarPath.substring(startFileIndexOf, pathIndexOf);
            }else {
                path = jarPath.substring(0,pathIndexOf);

            }
        }


        if(!StringUtils.isEmpty(path)) {
            filePath = path;
//            filePath = "/Users/lijunhong/Desktop/NMExportExcel/";
            logger.info("filePath:"+filePath);
            //TODO 这里实测是的数据
        }else {
            if(logger.isErrorEnabled()) logger.error("Not found .jar path");
        }
    }

    /**
     * 将获取到的文件路径返
     * @return
     */
    public static String getFilePath(){
        return filePath;
    }

    /**
     * 返回文件名,文件名通过当前时间明决定,后缀为xls
     * @return
     */
    private static String getFileName() {
        Date date = new Date();
        long time = date.getTime();
        return time + ".xls";
    }


    /**
     * 根据Model和数据创建Excel文件
     * @param metaDataList  生成Excel的数据
     */
    public static String createExcelByModel(List<List<MetaData>> metaDataList, int count){
        String fileName = getFileName();
        if(metaDataList != null) {
            ExcelModel excelModel = new ExcelModel(fileName,filePath);
            excelModel.createExcelTableHeader();
            excelModel.createExcelTableBody(metaDataList);
        }
        return fileName;
    }


    /**
     * 删除指定文件名的文件
     * @param fileName
     */
    public static void deleteExcelFile(String fileName){
        File file = new File(filePath+fileName);
        if(file.exists()){
            file.delete();
        }
    }

}
