package cn.itcast.bos.quartz;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.service.IWorkbillService;

public class Mailjobs {

	private String username;
	
	private String password;
	
	private String smtpServer;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSmtpServer() {
		return smtpServer;
	}


	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}


	@Resource
	private IWorkbillService workbillService;
	
	public void sendEmail() throws Exception{
		//1.查询出所有工单类型是新单且取件状态是未取件所有的工单
		List<Workbill> list = workbillService.findWorkBills();
		if(list != null && list.size()>0){
			//创建邮箱的session
			final Properties mailProps = new Properties();
			mailProps.setProperty("mail.smtp.host", this.getSmtpServer());
			mailProps.setProperty("mail.smtp.auth", "true");
			mailProps.setProperty("mail.username", this.getUsername());
			mailProps.setProperty("mail.password", this.getPassword());
			
			// 1.2构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticator = new Authenticator(){
				@Override					 
				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名、密码
					String userName = mailProps.getProperty("mail.username");
					String password = mailProps.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
				
			};
			
			Session session = Session.getInstance(mailProps, authenticator);
			
			//2.有需要派送的工单，给该工单对应的取派员发邮件
			
			for (Workbill workbill : list) {
				
				//1.3通过session创建消息对象
				MimeMessage message = new MimeMessage(session);
				//1.4设置发件人
				InternetAddress from = new InternetAddress(mailProps.getProperty("mail.username"));
				message.setFrom(from);
				
				Staff staff = workbill.getStaff();
				String email = staff.getEmail();
				
				InternetAddress to = new InternetAddress();
				//1.5设置收件人
				message.setRecipient(RecipientType.TO, to);
				//1.6设置主题
				message.setSubject("系统邮件-工单通知");
				//1.7设置邮箱内容
				message.setContent(workbill.toString(), "text/html;charset=utf-8");
				//1.8发送邮件
				Transport.send(message);
			}
			
		}
		
	}
}
