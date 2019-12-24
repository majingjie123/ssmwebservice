package com.xjst.controller;

import com.xjst.utils.fileutils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "down",description = "下载文件相关接口")
public class DownController {
    @RequestMapping("downFile")
    @ApiOperation(value = "downFile",notes = "下载文件相关接口",httpMethod = "POST")
    public void downFile(@ApiParam(value = "请求",required = true) HttpServletRequest request, @ApiParam(value = "响应",required = true) HttpServletResponse response) throws Exception {
        String[] path = {"E:\\12306Bypass_1.13.17.zip",
                "C:\\Users\\Administrator\\Desktop\\timg.jpg",
                "C:\\Users\\Administrator\\Desktop\\timg.jpg"
        };
        List<String> list = FileUtils.BachFileDown(path, "吗.zip", request, response);
    }
    @RequestMapping("upload")
    @ApiOperation(value = "upload",notes = "上传文件相关接口",httpMethod = "POST")
    public void upload(@ApiParam(value = "file",required = true) @RequestParam("file") MultipartFile file,@ApiParam(value = "请求",required = true) HttpServletRequest request){
        try {
            FileUtils.fileUpload(file,request,"E:\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
