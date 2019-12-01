package com.xjst.commpent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoCommpent {
    @Scheduled(cron = "*/2 * * * * ?")
    public void test(){
        System.out.println("定时任务执行");
    }
}
