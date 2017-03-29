package com.sobey.exportExcel.model;

import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created by lijunhong on 17/3/28.
 *
 * 内蒙古台:
 * 导出的Excel格式为:
 * 素材名
 * 时长
 * 入库时间
 */
public class ExcelModel {


    private static Logger logger = Logger.getLogger(ExcelModel.class);

    private final String EXCEL_FILE_SHEET_NAME = "素材信息导出";    //Excel文件中的工作薄默认创建名字

    private String fileName;                    //文件名
    private String path;                        //文件存放路径
    private String filePath;                    //文件存放路径和文件名
    private HSSFWorkbook workbook = null;       //Excel对象
    private HSSFSheet sheet = null;             //Excel的工作簿对象

    /**
     * 这里需要提供文件名文件路径和文件类型然后才能创建Excel
     * @param fileName
     * @param path
     */
    public ExcelModel(String fileName,String path){
        this.path = path;
        this.fileName = fileName;
//        if(ExcelFileUtils.isWindosSystem){
//            this.filePath = path + "/" +fileName;
//        }else {
//            this.filePath = path + fileName;
//        }
        this.filePath = path + "/" + fileName;



        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(EXCEL_FILE_SHEET_NAME);
    }


    /**
     * 本方法主要是根据提供的数据构造一张Excel表
     */
    public void createExcelTableHeader(){

        sheet.setColumnWidth(1,70*256);             //设置素材名
        sheet.setColumnWidth(2,28*256);             //设置时长
        sheet.setColumnWidth(3,28*256);             //设置导出时间


        HSSFRow row = null;
        HSSFCell cell = null;

        HSSFCellStyle titleStyle = workbook.createCellStyle();
        CellRangeAddress range = null;
        //设置表名
        row = sheet.createRow(0);
        range = new CellRangeAddress(0,0,0,4);
        sheet.addMergedRegion(range);
        cell = row.createCell(0);
        cell.setCellValue("检索素材信息");
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellStyle(titleStyle);

        HSSFCellStyle style = getHssfCellStyle();     //获取边框


        //设置第一行的元数据
        row = sheet.createRow(1);
        cell = row.createCell(1);
        cell.setCellValue("素材名");
        cell.setCellStyle(style);


        cell = row.createCell(2);
        cell.setCellValue("时长");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("入库时间");
        cell.setCellStyle(style);

        writerExcel();

    }


    public int createExcelTableBody(){

       return 0;
    }

    /**
     * 设置边框
     * @return
     */
    private HSSFCellStyle getHssfCellStyle() {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
    }

    /**
     * 用来将内存中的Excel写入到指定路劲的文件中
     */
    private void writerExcel() {
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            if(logger.isErrorEnabled()) logger.error("生成主题一览Excel文件出现错误"+e);
        }
    }


}
