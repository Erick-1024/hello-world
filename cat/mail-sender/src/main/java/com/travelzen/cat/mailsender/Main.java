package com.travelzen.cat.mailsender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.util.TopsAppRegistry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
			b.option(ChannelOption.TCP_NODELAY, Boolean.TRUE);
			b.group(bossGroup, workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .handler(new LoggingHandler(LogLevel.INFO))
			 .childHandler(channelInitializer());
			Channel ch = b.bind(getBoundIP(), getListeningPort()).sync().channel();
			logger.info("启动netty server完成");
			ch.closeFuture().sync();
		} catch(Exception e){
			logger.error("启动netty server异常", e);
			throw new RuntimeException(e);
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private static String getBoundIP(){
		return TopsAppRegistry.getLocalIP();
	}
	
	private static ChannelInitializer<SocketChannel> channelInitializer(){
		return new ChannelInitializer<SocketChannel>(){
			@Override
			public void initChannel(SocketChannel ch) {
				channelPipeline(ch);
			}
		};
	}
	
	private static ChannelPipeline channelPipeline(Channel ch){
		ChannelPipeline p = ch.pipeline();
		p.addLast("httpCodec", new HttpServerCodec());
		p.addLast("aggegator", new HttpObjectAggregator(64 * 1024 * 1024));
		p.addLast("protocol", new HttpBaseMailSender());
		return p;
	}
	
	private static int getListeningPort(){
		return 12280;
	}

}
