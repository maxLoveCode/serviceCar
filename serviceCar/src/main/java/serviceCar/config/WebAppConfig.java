package serviceCar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import serviceCar.interceptor.SessionInterceptor;
import serviceCar.interceptor.SystemWebSocketHandler;
import serviceCar.interceptor.TokenInterceptor;
import serviceCar.interceptor.WebSocketHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebAppConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	// token检查路径 session不检查
	static String[] tokenCheckUrl = new String[] { "/v1_0/**", "/v1_1/**" };
	// token不检查路径
	static String[] tokenNotCheckUrl = new String[] {};
	// session检查路径
	static String[] sessionCheckUrl = new String[] { "/**" };
	// session不检查路径
	static String[] sessionNotCheckUrl = new String[] { "/login", "/api", "/swagger-resources/**", "/v2/**" };

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// token验证拦截器
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns(tokenCheckUrl)
				.excludePathPatterns(tokenNotCheckUrl);

		// session验证拦截器
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns(sessionCheckUrl)
				.excludePathPatterns(tokenCheckUrl).excludePathPatterns(sessionNotCheckUrl);
	}

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler(), "/webSocketServer")
				.addInterceptors(new WebSocketHandshakeInterceptor());//支持websocket 的访问链接

		registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer")
				.addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();//不支持websocket 的访问链接
	}

	@Bean
	public WebSocketHandler systemWebSocketHandler() {
		return new SystemWebSocketHandler();
	}
}
