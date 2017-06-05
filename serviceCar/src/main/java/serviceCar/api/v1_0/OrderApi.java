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
	
	@ApiOperation(value = "创建订单", notes = "获取订单列表，分页待做")
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> placeOrder(@RequestParam String access_token,
			@RequestBody Order order){
		Map<String, Object> msg = new HashMap<String, Object>();
		orderService.placeOrder(order);
		return ResponseEntity.ok(msg);	
	}
	
	@ApiOperation(value = "订单列表", notes = "获取订单列表，分页待做")
	@RequestMapping(value = "/orderList", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> orderList(@RequestParam String access_token){
		Map<String, Object> msg = new HashMap<String, Object>();
		List<Order>result = orderService.getOrderList();
		msg.put("data", result);
		return ResponseEntity.ok(msg);	
	}

}
