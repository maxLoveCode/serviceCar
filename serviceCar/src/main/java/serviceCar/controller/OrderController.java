package serviceCar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pojo.User;
import serviceCar.dto.BaseCondition;
import serviceCar.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "order/index";
	}
	
	/**
	 * 获得列表数据
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Map<String,Object> list(BaseCondition condition) {
		Page<User> pager = PageHelper.startPage(condition.getPage(), condition.getRows());// 分页类
		List<HashMap<String, Object>> list = orderService.getOrderList(null);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", pager.getTotal());
		result.put("rows", list);
		return result;
	}
}
