package com.sobey.exportExcel.export;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lijunhong on 17/3/28.
 */
@CrossOrigin(origins = "*")
@RestController
public class ExportExcelService {

    //根据请求提供导出接口,需要实现浏览器兼容跨域问题,测试
    @RequestMapping(value = "/hello",method = {RequestMethod.GET,RequestMethod.POST})
    public String getAjax(HttpServletRequest request) {
//        String value = request.getParameter("_");
//        String json = "{reslut:'true'}";
//        System.out.println(value);
        return "hello world";
    }

    @RequestMapping(value = "/getExcelData",method = {RequestMethod.GET,RequestMethod.POST})
    public String getExcelData(){
        //获取数据
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/download",method = {RequestMethod.GET,RequestMethod.POST})
    public void download(){

    }



}
