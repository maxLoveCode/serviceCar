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

import pojo.Car;
import pojo.Department;
import pojo.User;
import serviceCar.dto.BaseCondition;
import serviceCar.service.UserService;
import serviceCar.service.UtilService;

@Controller
@RequestMapping("/util")
public class UtilController {

	@Autowired
	UserService userService;
	
	@Autowired
	UtilService utilService;
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
	

	@ResponseBody
	@RequestMapping(value = "/departList")
	public List<Department> departList() {
		List<Department> list = utilService.getDepartmentList();
		return list;
	}
	

	@ResponseBody
	@RequestMapping(value = "/carList")
	public List<Car> carList() {
		List<Car> list = utilService.getCarList();
		return list;
	}
	

	@ResponseBody
	@RequestMapping(value = "/driverList")
	public List<HashMap<String, Object>> driverList() {
		List<HashMap<String, Object>> list = userService.getDriverList();
		return list;
	}
}
