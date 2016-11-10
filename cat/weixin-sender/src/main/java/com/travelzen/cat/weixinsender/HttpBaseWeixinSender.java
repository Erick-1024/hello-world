package com.travelzen.cat.weixinsender;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.travelzen.cat.weixinsender.message.WeixinTextMessage;
import com.travelzen.cat.weixinsender.response.WeixinConnection;
import com.travelzen.cat.weixinsender.response.WeixinReturnCode;

public class HttpBaseWeixinSender extends SimpleChannelInboundHandler<DefaultFullHttpRequest> {
	
	private static Logger logger = LoggerFactory.getLogger(HttpBaseWeixinSender.class);
	
	private HttpURLConnection httpURLConnection;
	
	private WeixinConnection connection;
	
	private WeixinReturnCode returnCode;
	
	private WeixinTextMessage textMessage;

	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
	
	public void channelRead0(ChannelHandlerContext ctx, DefaultFullHttpRequest httpRequest) throws Exception{
		WeixinRequest weixinRequest = extractWeixinRequest(httpRequest);
		establishConnection(weixinRequest);
		sendWeixinText(weixinRequest);
		successAck(ctx, weixinRequest);
		logger.info("微信发送成功");
	}
	
	private void successAck(ChannelHandlerContext ctx, WeixinRequest weixinRequest) {
		String successAck = weixinRequest.getSuccessAck();
		if(StringUtils.isBlank(successAck))
			successAck = "success";
		DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(successAck.getBytes(CharsetUtil.UTF_8)));
		httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, httpResponse.content().readableBytes());
		ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
	}
	
	private void establishConnection(WeixinRequest weixinRequest) throws IOException{
		URL url = new URL(weixinRequest.getConnection_url());
		String content = sendHttpRequest(url, "GET", "");
		if(content.contains("errcode")){
			returnCode = JSON.parseObject(content, WeixinReturnCode.class);
			if(!returnCode.getErrcode().equals("0")){
				throw new IOException(content);
			} 
		}else{
			connection = JSON.parseObject(content, WeixinConnection.class);
		}
		weixinRequest.setAccess_token(connection.getAccess_token());
	}
	
	private void sendWeixinText(WeixinRequest weixinRequest) throws Exception{
		textMessage = convertWeixinRequest(weixinRequest);
		URL url = new URL(weixinRequest.getSend_url());
		String content = sendHttpRequest(url, "POST", JSON.toJSONString(textMessage));
		returnCode = JSON.parseObject(content, WeixinReturnCode.class);
		System.out.println(returnCode.getErrcode());
		if(returnCode.getErrcode().equals("-1")){
			throw new IOException("微信系统繁忙");
		} else if(!returnCode.getErrcode().equals("0")){
			throw new IOException(content);
		} 
	}
	
	private String sendHttpRequest(URL url, String requestMethod, String json) throws IOException{
		httpURLConnection = (HttpURLConnection) url.openConnection(); 
		httpURLConnection.setRequestMethod(requestMethod); 
		httpURLConnection.setDoOutput(true);
		OutputStream os = httpURLConnection.getOutputStream();
		os.write(json.getBytes());
		os.flush();
		os.close();
		InputStream is = httpURLConnection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String ss;
		while ((ss = br.readLine()) != null) {
			sb.append(ss);
			}
		String content = new String(sb);
		br.close();
		return content;
	}
	
	private WeixinTextMessage convertWeixinRequest(WeixinRequest weixinRequest){
		WeixinTextMessage textMessage = new WeixinTextMessage();
		textMessage.setTouser(weixinRequest.getTouser());
		textMessage.setAgentid(weixinRequest.getAgentid());
		textMessage.getText().setContent(weixinRequest.getContent());
		textMessage.setSafe(weixinRequest.getSafe());
		return textMessage;
	}
	
	private WeixinRequest extractWeixinRequest(DefaultFullHttpRequest httpRequest) throws Exception{
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), httpRequest);
		WeixinRequest weixinRequest = new WeixinRequest();
		weixinRequest.setTouser(getPostParamValue(decoder, "touser"));
		weixinRequest.setCorpid(getPostParamValue(decoder, "corpid"));
		weixinRequest.setCorpsecret(getPostParamValue(decoder, "corpsecret"));
		weixinRequest.setAgentid(getPostParamValue(decoder, "agentid"));
		weixinRequest.setContent(getPostParamValue(decoder, "content"));
		weixinRequest.setSafe(getPostParamValue(decoder, "safe"));
        weixinRequest.setSuccessAck(getPostParamValue(decoder, "successAck"));
		return weixinRequest;
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
		logger.error("微信发送异常", cause);
		String errorMsg = ExceptionUtils.getFullStackTrace(cause);
		if(StringUtils.isBlank(errorMsg))
			errorMsg = "error";
		DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(errorMsg.getBytes(CharsetUtil.UTF_8)));
		httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, httpResponse.content().readableBytes());
		ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
	}
}
