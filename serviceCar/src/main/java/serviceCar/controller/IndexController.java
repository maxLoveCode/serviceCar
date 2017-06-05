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

import pojo.User;
import serviceCar.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	@Autowired
	UserService userService;

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
	 * 登录操作
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(String username, String password, Model model, HttpSession session) {
		if (StringUtils.isEmpty(username)) {
			model.addAttribute("error", "用户名不能为空");
			return "login";
		}
		model.addAttribute("username", username);
		if (StringUtils.isEmpty(password)) {
			model.addAttribute("error", "密码不能为空");
			return "login";
		}
		User admin = userService.findUserByUsername(username);
		if (StringUtils.isEmpty(admin)) {
			model.addAttribute("error", "用户名不存在");
			return "login";
		}
		if (!admin.getPassword().equals(password)) {
			model.addAttribute("error", "用户名或密码错误");
			return "login";
		}
		
		//更新登录时间
		admin.setLogintime(new Date());
		userService.updateByPrimaryKey(admin);

		session.setAttribute(SESSION_ID, session.getId());
		session.setAttribute(SESSION_NAME, admin);
		session.setAttribute(WEBSOCKET_USERNAME, String.valueOf(admin.getId()));
		return "index";
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
}
