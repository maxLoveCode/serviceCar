package dto;

public class Message {
	private boolean success;// 成功标志
	private String msg;// 信息(成功或错误信息)

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
