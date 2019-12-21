package com.xjst.test;

import com.xjst.dao.User;
import com.xjst.utils.emailutils.EmailUtils;
import com.xjst.utils.emailutils.RandomUtils;
import com.xjst.utils.fileutils.FileUtils;
import org.junit.jupiter.api.Test;

public class TestFile {
    @Test
    public void downFile() throws Exception {
        String parth = "C:\\Users\\Administrator\\Desktop\\timg (1).jpg";
        String dexc = "F:\\mm.bmp";
        boolean b = FileUtils.fileMove(parth, dexc);
        System.out.println(b);
    }

    @Test
    public void sendEmail(){
        User user = new User();
        user.setName("秦洋洋");
        user.setEmail("qinyangy@outlook.com");
        String subject = "秦洋洋先生看到请微信回复一下";
        StringBuilder content = new StringBuilder();
        content.append("秦洋洋先生你好：");
        content.append("<br/>");
        content.append("        收到邮件回复一下谢谢");
        String myAccount = "1334015172@qq.com";
        String pass = "myaimbysdtkbjdie";
        String smtphost = "smtp.qq.com";
        String protocol = "smtp";
        EmailUtils.sendEmail(user,subject,content.toString().trim(),"你马景杰大哥邮件发送测试用的",myAccount,pass,smtphost,protocol,"true");
    }
}
