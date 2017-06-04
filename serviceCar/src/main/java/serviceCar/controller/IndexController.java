package serviceCar.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class IndexController extends BaseController {


	/**
	 * 跳转至api文档
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public void api(HttpServletResponse response) {
		try {
			response.sendRedirect("/swagger-ui.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	} 

	/**
	 * 登出页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute(SESSION_ID, null);
		session.setAttribute(SESSION_NAME, null);
		session.invalidate();
		return "login";
	}

	/**
	 * 主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		return "index";
	}
}
