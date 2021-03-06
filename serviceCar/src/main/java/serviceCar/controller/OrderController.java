package serviceCar.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.ApiOperation;
import pojo.Order;

import pojo.User;
import serviceCar.dto.BaseCondition;
import serviceCar.dto.Message;
import serviceCar.service.JpushService;
import serviceCar.service.OrderService;
import serviceCar.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	JpushService pushService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "order/index";
	}
	
	@RequestMapping(value = "/reviewIndex", method = RequestMethod.GET)
	public String reviewIndex() {
		return "order/reviewIndex";
	}
	
	@RequestMapping(value = "/statIndex", method = RequestMethod.GET)
	public String statIndex() {
		return "order/statIndex";
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
		List<HashMap<String, Object>> list = orderService.getOrderList(null,condition.getKeywords(),null);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", pager.getTotal());
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 获得列表数据
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reviewList")
	public Map<String,Object> reviewList(BaseCondition condition, HttpSession session) {
		Page<User> pager = PageHelper.startPage(condition.getPage(), condition.getRows());// 分页类
		System.out.println(condition.getKeywords());
		System.out.println(condition.getDate());
		Integer userType = (Integer) session.getAttribute(SESSION_TYPE);
		System.out.println("userType"+userType);
		List<HashMap<String, Object>> list = null;
		if(userType == User.SECRETARY)
		{
			list = orderService.getOrderList(5,condition.getKeywords(),null);
		}
		else
		{
			list = orderService.getOrderList(3,condition.getKeywords(),condition.getDate());
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", pager.getTotal());
		result.put("rows", list);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/review")
	public Message review(Order order) {
		Order selected = orderService.selectByPrimaryKey(order.getId());
		Boolean result = orderService.completeOrder(selected);
		if(result)
			return successMessage();
		else
			return failMessage("订单状态"+order.getOrderStatus());
	}

	@ResponseBody
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public Message placeOrder(@RequestBody Order order){
		
		orderService.placeOrder(order);
		
		Map<String, String> extra = new HashMap<String, String>();
		String content = "您被安排了订单，目的地：" +order.getDest();
		pushService.notifyWithExtra(order.getDriverId(), "您被安排了一个新订单", content, extra);

		return successMessage();
	}
}
