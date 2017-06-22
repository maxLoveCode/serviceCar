package pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sc_user")
public class User extends Base {
	public static final Integer DRIVER = 1;
	public static final Integer SECRETARY = 2;
	public static final Integer FINANCE = 3;
	public static final Integer MANAGEMENT = 4;

	private String username;
	private String password;
	private Date logintime;

	private Integer usertype;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
}
