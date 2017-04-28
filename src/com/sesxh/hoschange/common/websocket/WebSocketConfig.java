package com.sesxh.hoschange.common.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * 
 * @Title:WebSocketConfig
 * @Description:
 * @author Ning
 * @date 2017年3月17日
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	/**
	 * SpringWebSocket注册
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		// 允许连接的域,只能以http或https开头
		String[] allowsOrigins = { "http://www.xxx.com" };

		// WebSocket通道
		registry.addHandler(chatWebSocketHandler(), "/webSocketServer").setAllowedOrigins("*")
				.addInterceptors(myInterceptor());
		registry.addHandler(chatWebSocketHandler(), "/sockjs/webSocketIMServer").setAllowedOrigins(allowsOrigins)
				.addInterceptors(myInterceptor()).withSockJS();
	}

	/**
	 * SpringWebSocket Handler
	 * 
	 * @Title: chatWebSocketHandler
	 * @author Ning
	 * @data:2017年3月17日
	 * @return:MyWebSocketHandler
	 * @throws:
	 */
	@Bean
	public MyWebSocketHandler chatWebSocketHandler() {
		return new MyWebSocketHandler();
	}

	/**
	 * SpringWebSocket拦截
	 * 
	 * @Title: myInterceptor
	 * @author Ning
	 * @data:2017年3月17日
	 * @return:WebSocketHandshakeInterceptor
	 * @throws:
	 */
	@Bean
	public WebSocketHandshakeInterceptor myInterceptor() {
		return new WebSocketHandshakeInterceptor();
	}

	/**
	 * SpringWebSocket引擎，手动修改websocket通信支持的最大文本限制
	 * SpringWebSocket默认最大通信文本为8K
	 * @Title: createWebSocketContainer
	 * @author Ning
	 * @data:2017年3月17日
	 * @return:ServletServerContainerFactoryBean
	 * @throws:
	 */
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(1024 * 1000);
		container.setMaxBinaryMessageBufferSize(1024 * 1000);
		return container;
	}

}
