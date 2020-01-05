package com.xjst.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 演示
 */
@Api(value = "KafkaController",description = "kafka相关接口")
@Controller
public class KafkaController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @GetMapping("/sendMsg")
    @ApiOperation(value = "kafka信息发送",notes = "kafka信息发送",httpMethod = "get")
    public String msgSend(){
        kafkaTemplate.send("first","张三");
        return "/static/file.jsp";
    }
}
