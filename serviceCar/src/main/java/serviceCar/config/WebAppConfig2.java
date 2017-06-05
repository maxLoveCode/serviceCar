package serviceCar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import serviceCar.interceptor.SystemWebSocketHandler;
import serviceCar.interceptor.WebSocketHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebAppConfig2 implements WebSocketConfigurer {

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(), "/webSocketServer")
				.addInterceptors(new WebSocketHandshakeInterceptor());// 支持websocket的访问链接

		registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer")
				.addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();// 不支持websocket的访问链接
	}

	@Bean
	public WebSocketHandler systemWebSocketHandler() {
		return new SystemWebSocketHandler();
	}
}
