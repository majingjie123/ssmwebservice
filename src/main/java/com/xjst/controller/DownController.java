package com.xjst.controller;

import com.xjst.utils.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class DownController {
    @RequestMapping("downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] path = {"E:\\12306Bypass_1.13.17.zip",
                "C:\\Users\\Administrator\\Desktop\\timg.jpg",
                "C:\\Users\\Administrator\\Desktop\\timg.jpg"
        };
        List<String> list = FileUtils.BachFileDown(path, "吗.zip", request, response);
    }
    @RequestMapping("upload")
    public void upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        try {
            FileUtils.fileUpload(file,request,"E:\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
