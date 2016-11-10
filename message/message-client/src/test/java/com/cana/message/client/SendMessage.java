package com.cana.message.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.message.enums.SmsMessageType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class SendMessage{
	
	@Resource(name="mailRabbitTemplate")
	private RabbitTemplate mailRabbitTemplate;
	
	@Resource(name="notificationRabbitTemplate")
    private RabbitTemplate notificationRabbitTemplate;

	@Resource(name="smsMessageRabbitTemplate")
	private RabbitTemplate smsMessageRabbitTemplate;
	
	@Test
	public void sendMail(){
		MailMessageDTO message = new MailMessageDTO();
		//message.setFrom("shui.ren@travelzen.com");;
		message.setReceiver("791003770@qq.com");
		message.setSubject("mailTestSubject");
		message.setContent("&nbsp;&nbsp;&nbsp;&nbsp;mailTestContent");
		mailRabbitTemplate.convertAndSend(message, new MessagePostProcessor(){

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				//MessageProperties properties = message.getMessageProperties();
				//properties.setExpiration(String.valueOf(30000 * 1000));
				return message;
			}

		});
	}
	@Test
	public void sendMail2(){
		MailMessageDTO message = new MailMessageDTO();
		message.setContentType(MailContentType.HTML);
		message.setReceiver("yuxiaoyu@canacorp.net");
		message.setSubject("mailTestSubject");
		message.setContent("【CANA金融】尊敬的"+"1111"+"用户：<br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 您本次信韵融申请已通过审核，相关信息如下：可申请用款金额："+"1000"+"元<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 请点击链接以下链接激活 激活链接"+" http://op.canacorp.net/facade/userActivation/goToActive?userId=201605250064&securityCode=AlH0kVWLfV"+" <br><br> 如有疑问，请与CANA金融联系，电话："+"13912992334"+"。");
		//List<String> list = new ArrayList<>();
		//list.add("/home/xiaoyu/code/1.docx");
		//message.setAttachments(list);
		/*mailRabbitTemplate.convertAndSend(message, new MessagePostProcessor(){
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				//MessageProperties properties = message.getMessageProperties();
				//properties.setExpiration(String.valueOf(30000 * 1000));
				return message;
			}
			
		});*/
		mailRabbitTemplate.convertAndSend(message);
	}
	
	@Test
    public void sendNotification(){
	    NotificationMessageDTO message = new NotificationMessageDTO();
	    message.setContent("helllll");
	    message.setDetailURL("url");
	    message.setReceiveCustomerId("asdfasdf");
	    message.setSendUserId("asdf");
	    message.setType(NotificationType.CREATE_SUPERVISION);
        notificationRabbitTemplate.convertAndSend(message);
    }
	
	@Test
	public void sendSmsMessage(){
		SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
		smsMessageDTO.setReceivePhoneNum("13912992334");
		smsMessageDTO.setSmsMessageType(SmsMessageType.CODE);
		smsMessageDTO.setPrefix("【Cana金融】");
		smsMessageDTO.setContent("您本次注册的验证码为112255，请妥善处理。");
		smsMessageRabbitTemplate.convertAndSend(smsMessageDTO);
	}
	@Test
	public void sendSmsMessage2(){
		SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
		smsMessageDTO.setReceivePhoneNum("13912992334");
		smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
		smsMessageDTO.setPrefix("【CANA金融】");
		smsMessageDTO.setContent("尊敬的客户：    您的资质暂不符合申请“{product}”产品的要求，如您符合条件后，我们将及时通知您！【CANA金融】                           谢谢！");
		smsMessageRabbitTemplate.convertAndSend(smsMessageDTO);
	}
}
