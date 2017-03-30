package com.sobey.exportExcel.export;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sobey.exportExcel.model.MetaData;
import com.sobey.exportExcel.util.ExcelFileUtil;

/**
 * Created by lijunhong on 17/3/28.
 */
@CrossOrigin(origins = "*")
@RestController
public class ExportExcelService {

    private Logger logger = Logger.getLogger(ExportExcelService.class);

    public static List<List<MetaData>> metaDatas = new ArrayList<>();		//存放前台需要生成Excel的元数据
    public int meataDataLength = 0;											//传输过来的数据条数的统计,用于判断是否传输完毕
    public static Boolean isCreateExcel = false;							//是否能创建Excel文件标记


    //根据请求提供导出接口,需要实现浏览器兼容跨域问题,测试(能支持IE进行跨域)
    @RequestMapping(value = "/getMetaData",method = {RequestMethod.GET,RequestMethod.POST})
    public String getAjax(HttpServletRequest request) {
        String dataList = request.getParameter("datalist");
        if(dataList!=null) {
            List<MetaData> metaDatasTemp = JSON.parseArray(dataList, MetaData.class);
            meataDataLength += metaDatasTemp.size();
            metaDatas.add(metaDatasTemp);
            if(logger.isInfoEnabled()) logger.info("get ExcelMetaData data count:"+metaDatasTemp.size());
        }else {
            if(logger.isWarnEnabled()) logger.warn("get Excel data is null,not MeataData data");
        }
        return "success";
    }

    /**
     * 提供数据上传的接口
     * @return
     */
    @RequestMapping(value = "/getExcelData",method = {RequestMethod.GET,RequestMethod.POST})
    public String getExcelData(){
        //获取数据

        return null;
    }

    /**
     * 提供下载的接口
     * @param res
     * @throws InterruptedException
     */
    @CrossOrigin
    @RequestMapping(value = "/download",method = {RequestMethod.GET,RequestMethod.POST})
    public void download(HttpServletResponse res) throws InterruptedException {
        Thread.sleep(1000L);									//为了防止下载的请求快于数据请求
        if(metaDatas != null) {
            try {
                String fileName = ExcelFileUtil.createExcelByModel(metaDatas, meataDataLength);
                downloadExcel(res, ExcelFileUtil.getFilePath(), fileName);
                ExcelFileUtil.deleteExcelFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                metaDatas.clear();    //将集合中的数据清零
                meataDataLength = 0;    //将统计的条数清零
            }
        }

    }

    /**
     * 提供下载功能
     * @param res
     * @param filePath 下载文件路径
     * @param fileName 下载文件文件名
     */
    private void downloadExcel(HttpServletResponse res, String filePath, String fileName) throws IOException {
        System.out.println("filename"+fileName);
        System.out.println("filepath"+filePath);

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename="+fileName);
        File file = null;
        if(ExcelFileUtil.isWindosSystem){
            file = new File(filePath+"/"+fileName);
        }else {
            file=new File(filePath+fileName);
        }
        System.out.println("filePath:--"+filePath+":::"+fileName);
//		File file = new File("/Users/lijunhong/Desktop/jsonp/Test.xml");
        res.setContentLengthLong(file.length());
        InputStream in = new FileInputStream(file);
        OutputStream out = res.getOutputStream();

        int len = 0;
        byte[] b = new byte[10*1024];
        while ((len=in.read(b))!=-1){
            out.write(b,0,len);
        }
        in.close();
        out.flush();
        out.close();

    }


}
