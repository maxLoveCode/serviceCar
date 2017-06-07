package serviceCar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pojo.User;
import serviceCar.dto.BaseCondition;
import serviceCar.service.UserService;

@Controller
@RequestMapping("/util")
public class UtilController {

	@Autowired
	UserService userService;
	/**
	 * 获得列表数据
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/typeList")
	public List<HashMap<String, Object>> list() {
		List<HashMap<String, Object>> list = userService.getUserTypes();
		return list;
	}
}
