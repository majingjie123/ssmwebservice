package com.xjst.test;

import com.xjst.dao.User;
import com.xjst.utils.emailutils.EmailUtils;
import com.xjst.utils.emailutils.RandomUtils;
import com.xjst.utils.fileutils.FileUtils;
import com.xjst.utils.propertiesutils.PropertiesUtils;
import org.junit.jupiter.api.Test;

public class TestFile {
    @Test
    public void downFile() throws Exception {
//        String parth = "C:\\Users\\Administrator\\Desktop\\timg (1).jpg";
//        String dexc = "F:\\mm.bmp";
//        boolean b = FileUtils.fileMove(parth, dexc);
//        System.out.println(b);
        String name = PropertiesUtils.getProperties("name");
        System.out.println(name);
    }

    @Test
    public void sendEmail() throws Exception {
        User user = new User();
        user.setName("秦洋洋");
        user.setEmail("majingjiehe@163.com");
        String subject = "秦洋洋先生看到请微信回复一下";
        StringBuilder content = new StringBuilder();
        content.append("秦洋洋先生你好：");
        content.append("<br/>");
        content.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收到邮件回复一下谢谢!");
        String myAccount = PropertiesUtils.getProperties("myAccount");
        String pass = PropertiesUtils.getProperties("myPass");
        String smtphost = PropertiesUtils.getProperties("smtphost");
        String protocol = PropertiesUtils.getProperties("protocol");
        EmailUtils.sendEmail(user,subject,content.toString().trim(),"你马景杰大哥邮件发送测试用的",myAccount,pass,smtphost,protocol,"true");
    }
}
