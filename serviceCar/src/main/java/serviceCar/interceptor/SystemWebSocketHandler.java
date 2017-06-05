package serviceCar.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import serviceCar.controller.BaseController;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final List<WebSocketSession> users = new ArrayList<WebSocketSession>();

	/**
	 * 连接上之后
	 */
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		String username = (String) session.getAttributes().get(BaseController.WEBSOCKET_USERNAME);
		if(username!= null) {
			//int count = webSocketService
		}
	}

	/**
	 * 关闭连接
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session);
	}

	/**
	 * 发送消息
	 */
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {

	}

	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线client发送消息
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 给某个client发送消息
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get(BaseController.WEBSOCKET_USERNAME).equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
