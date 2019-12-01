package com.xjst.controller;

import cn.com.webxml.MobileCodeWSSoap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "PhoneInfo",description = "获取手机接口")
public class PhoneController {
    @Autowired
    private MobileCodeWSSoap wsSoap;
    @GetMapping("getPhone")
    @ApiOperation(value = "获取手机信息",notes = "获取手机信息",httpMethod = "get")
    public String getPhone(@ApiParam(value = "手机号",required = true) String phone){
        String mobileCodeInfo = wsSoap.getMobileCodeInfo(phone, null);
        return mobileCodeInfo;
    }
}
