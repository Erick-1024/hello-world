package com.travelzen.cat.mailsender;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.util.MailUtil;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.util.CharsetUtil;


public class HttpBaseMailSender extends SimpleChannelInboundHandler<DefaultFullHttpRequest> {
	
	private static Logger logger = LoggerFactory.getLogger(HttpBaseMailSender.class);
	

	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	public void channelRead0(ChannelHandlerContext ctx, DefaultFullHttpRequest httpRequest) throws Exception{
		MailRequest mailRequest = extractMailRequest(httpRequest);
		sendMail(mailRequest);
		successAck(ctx, mailRequest);
		logger.info("邮件发送成功");
	}

	private void successAck(ChannelHandlerContext ctx, MailRequest mailRequest) {
		String successAck = mailRequest.getSuccessAck();
		if(StringUtils.isBlank(successAck))
			successAck = "success";
		DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(successAck.getBytes(CharsetUtil.UTF_8)));
		httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, httpResponse.content().readableBytes());
		ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
	}

	private void sendMail(MailRequest mailRequest) throws Exception{
		MailUtil mailUtil = new MailUtil();
		String host = mailRequest.getHost();
		if(StringUtils.isBlank(host))
			host = "shmail2.travelzen.com";
		mailUtil.setHost(host);
		mailUtil.setUsername(mailRequest.getUsername());
		mailUtil.setPassword(mailRequest.getPassword());
		mailUtil.setFrom(mailRequest.getFrom());
		mailUtil.setTo(mailRequest.getTo());
		mailUtil.setSubject(mailRequest.getSubject());
		mailUtil.setContentType("text/html;charset=utf-8");
		mailUtil.setContent(mailRequest.getContent());
		mailUtil.sendMail();
		
	}

	private MailRequest extractMailRequest(DefaultFullHttpRequest httpRequest) throws Exception{
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), httpRequest);
        MailRequest mailRequest = new MailRequest();
        mailRequest.setContent(getPostParamValue(decoder, "content"));
        mailRequest.setFrom(getPostParamValue(decoder, "from"));
        mailRequest.setTo(getPostParamValue(decoder, "to"));
        mailRequest.setSubject(getPostParamValue(decoder, "subject"));
        mailRequest.setUsername(getPostParamValue(decoder, "username"));
        mailRequest.setPassword(getPostParamValue(decoder, "password"));
        mailRequest.setHost(getPostParamValue(decoder, "host"));
        mailRequest.setSuccessAck(getPostParamValue(decoder, "successAck"));
		return mailRequest;
	}

	private String getPostParamValue(HttpPostRequestDecoder decoder, String paramName) throws Exception{
		InterfaceHttpData data = decoder.getBodyHttpData(paramName);
		if(data == null)
			return null;
		if (data.getHttpDataType() == HttpDataType.Attribute) {
		     Attribute attribute = (Attribute) data;
		     return attribute.getValue();
		}
		return null;
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.error("邮件发送异常", cause);
		String errorMsg = ExceptionUtils.getFullStackTrace(cause);
		if(StringUtils.isBlank(errorMsg))
			errorMsg = "error";
		DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(errorMsg.getBytes(CharsetUtil.UTF_8)));
		httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, httpResponse.content().readableBytes());
		ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
	}
}
