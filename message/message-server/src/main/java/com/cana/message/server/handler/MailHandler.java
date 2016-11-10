package com.cana.message.server.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.cana.message.mongo.entity.MailMessageHistory;
import com.cana.message.server.consts.Consts;
import com.cana.message.server.service.IMessageHistoryService;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.MessageType;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.util.MailUtil;

public class MailHandler {

	private MailUtil mailUtil;

	private IMessageHistoryService messageHistoryService;

	private Logger log = LoggerFactory.getLogger(MailHandler.class);

	public MailHandler(IMessageHistoryService messageHistoryService) {
		this.messageHistoryService = messageHistoryService;
		mailUtil = new MailUtil();
		mailUtil.setHost(Consts.MAIL_SMTP);
		mailUtil.setUsername(Consts.MAIL_AUTH_USERNAME);
		mailUtil.setPassword(Consts.MAIL_AUTH_PASSWORD);
	}

	public void handleMessage(MailMessageDTO mailMsg){
		String from = Consts.MAIL_FROM;
		String receiver = mailMsg.getReceiver();
		String subject = mailMsg.getSubject();
		String content = mailMsg.getContent();
		String contentType = MailContentType.HTML.getDesc();
		List<String> attachments = mailMsg.getAttachments();
		Map<String, String> images = mailMsg.getImages();
		if (StringUtils.isNotBlank(mailMsg.getFrom())) {
			from = mailMsg.getFrom();
		}
		if (mailMsg.getContentType() != null) {
			contentType = mailMsg.getContentType().getDesc();
		}
		log.info(
				"mail is ready for sending, FROM {} TO {}, SUBJECT {}, CONTENT {}",
				from, receiver, subject, content);
		if (attachments != null) {
			log.info("attachments size {}", attachments.size());
		}
		mailUtil.setFrom(Consts.MAIL_FROM);
		mailUtil.setTo(receiver);
		mailUtil.setSubject(subject);
		mailUtil.setContent(content);
		if (StringUtils.isEmpty(contentType)) {
			contentType = MailContentType.HTML.getDesc();
		}
		mailUtil.setContentType(contentType);
		mailUtil.setAttachments(attachments);
		mailUtil.setImages(images);
		MailMessageHistory history = new MailMessageHistory();
		history.setTitle(subject);
		history.setContent(content);
		history.setSendTo(receiver);
		history.setMsgType(MessageType.MAIL);
		history.setSendDateTime(DateTime.now());
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		log.info("收到邮件发送通知: "+ new Gson().toJson(mailMsg));
		try{
			boolean sendStatus = mailUtil.sendMail();
			log.info("邮件发送状态:"+sendStatus);
			history.setSendStatus(sendStatus);
		} catch (Exception e){
			log.error("处理消息异常", e);
			throw e;
		}finally{
			MDC.clear();
		}
		messageHistoryService.saveMailMessageHistory(history);
	}
}
