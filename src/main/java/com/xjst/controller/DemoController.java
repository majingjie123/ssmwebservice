package com.xjst.controller;

import com.xjst.dao.User;
import com.xjst.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "demo",description = "demo的相关接口")
public class DemoController {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Autowired
    private DemoService service;
    @GetMapping("/name")
    @ApiOperation(value = "获取所有的用户信息",notes = "获取所有的用户信息,参数为用户名",httpMethod = "get")
    public List<User> getName(@ApiParam(value = "用户名",required = true) String name){
        return service.getName();
    }

    @GetMapping("/nae")
    @ApiOperation(value = "获取所有的用户信息",notes = "获取所有的用户信息,参数为用户名",httpMethod = "get")
    public List<User> getNae(@ApiParam(value = "用户名",required = true) String name){
        return service.getName();
    }
}
