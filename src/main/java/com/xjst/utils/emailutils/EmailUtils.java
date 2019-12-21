package com.xjst.utils.emailutils;


import com.xjst.dao.User;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtils {
	/**
	 *
	 * @param user
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param sendName 发送人名字
	 * @param myAccount 发件人邮箱
	 * @param myPass 发件人授权码
	 * @param SMTPHost
	 * @param protocol 协议
	 * @param isFilter
	 */
	public static void sendEmail(User user,String subject,String content,String sendName,String myAccount,String myPass,String SMTPHost,String protocol,String isFilter){
		//邮箱设置
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", protocol);//
		prop.setProperty("mail.smtp.host", SMTPHost);//
		prop.setProperty("mail.smtp.auth", isFilter);//
		//1.创建会话
		Session session = Session.getDefaultInstance(prop);
		//开启开发者模式
		session.setDebug(true);
		//2.创建消息
		MimeMessage message = createMsg(session,myAccount,user,subject,content,sendName);
		//4.根据协议连接
		try {
			Transport tran = session.getTransport();
			//连接
			tran.connect(myAccount, myPass);
			//发送消息
			tran.sendMessage(message, message.getAllRecipients());
			//关闭
			tran.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//创建邮件消息
	private static MimeMessage createMsg(Session session, String myAccount, User user,String subject,String content,String personal) {
		//创建消息对象
		MimeMessage message = new MimeMessage(session);
		//3.组装消息
		try {
			//3.1设置发送方
			message.setFrom(new InternetAddress(myAccount, personal, "utf-8"));
			//3.2 设置接收方
            /*
			 * MimeMessage.RecipientType.TO
			 * MimeMessage.RecipientType.CC
			 * MimeMessage.RecipientType.BCC
			 * */
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(), user.getName(), "utf-8"));
			//3.3设置主题
			message.setSubject(subject,"utf-8");
			String ip = Inet4Address.getLocalHost().getHostAddress();
			//设置正文内容
			message.setContent(content,"text/html;charset=utf-8");
			//设置发送日期
			message.setSentDate(new Date());
			//保存消息
			message.saveChanges();
		} catch (UnsupportedEncodingException | MessagingException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
