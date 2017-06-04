package controller;

import dto.Message;

public class BaseController {

	public static final String SESSION_ID = "session_id";
	public static final String SESSION_NAME = "admin";
	public static final String WEBSOCKET_USERNAME = "websocket_username";

	protected Message successMessage() {
		Message msg = new Message();
		msg.setSuccess(true);
		msg.setMsg("操作成功");
		return msg;
	}

	protected Message failMessage(String message) {
		Message msg = new Message();
		msg.setSuccess(false);
		msg.setMsg(message);
		return msg;
	}
}
