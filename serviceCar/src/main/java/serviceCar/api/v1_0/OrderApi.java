package serviceCar.api.v1_0;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceCar.service.OrderService;

import io.swagger.annotations.ApiOperation;
import pojo.Order;

@RestController("OrderApi")
@RequestMapping("/v1_0")

public class OrderApi {
	
	@Autowired
	OrderService orderService;
	
	@ApiOperation(value = "创建订单", notes = "创建订单")
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> placeOrder(@RequestParam String access_token,
			@RequestBody Order order){
		Map<String, Object> msg = new HashMap<String, Object>();
		orderService.placeOrder(order);
		msg.put("msg","成功");
		return ResponseEntity.ok(msg);	
	}
	
	@ApiOperation(value = "订单列表", notes = "获取订单列表，分页待做")
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> orderList(@RequestParam String access_token){
		Map<String, Object> msg = new HashMap<String, Object>();
		List<HashMap<String, Object>>result = orderService.getOrderList();
		msg.put("data", result);
		return ResponseEntity.ok(msg);	
	}
	
	@ApiOperation(value = "司机接受订单", notes = "司机接受订单")
	@RequestMapping(value = "/acceptOrder", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> acceptOrder(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectByPrimaryKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.acceptOrder(order))
		{
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("msg","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}
	
	@ApiOperation(value = "司机开始订单", notes = "司机开始订单")
	@RequestMapping(value = "/start", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> startWorking(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectByPrimaryKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.startWorking(order))
		{
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("msg","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}
	
	@ApiOperation(value = "司机完成订单进入待审核状态", notes = "司机完成订单进入待审核状态")
	@RequestMapping(value = "/pendingForInspection", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> pendingForInspection(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectByPrimaryKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.pendingForInspection(order))
		{
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("msg","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}

	@ApiOperation(value = "秘书处审核通过", notes = "秘书处审核通过")
	@RequestMapping(value = "/completeInspection", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> completeInspection(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectByPrimaryKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.completeInspection(order))
		{
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("msg","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}
	
	@ApiOperation(value = "财务处查阅完毕", notes = "财务处查阅完毕")
	@RequestMapping(value = "/completeOrder", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> completeOrder(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectByPrimaryKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.completeOrder(order))
		{
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("msg","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}
}
