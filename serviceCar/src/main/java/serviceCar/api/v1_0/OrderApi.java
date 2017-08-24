package serviceCar.api.v1_0;

import java.security.Principal;
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
import serviceCar.service.AttendenceService;
import serviceCar.service.CarService;
import serviceCar.service.GPSService;
import serviceCar.service.JpushService;
import serviceCar.service.UserService;

import io.swagger.annotations.ApiOperation;
import serviceCar.dto.ExtraInfoModel;
import pojo.Car;
import pojo.Order;
import pojo.User;

@RestController("OrderApi")
@RequestMapping("/v1_0")

public class OrderApi {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	JpushService pushService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CarService carService;
	
	@Autowired
	AttendenceService attendenceService;
	
	@ApiOperation(value = "创建订单", notes = "创建订单")
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> placeOrder(@RequestParam String access_token,
			@RequestBody Order order,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		String acc = principal.getName();
		User user = userService.findUserByUsername(acc);
		
		if(user!=null)
		{
			order.setOwnerId(user.getId());
		}
		
		orderService.placeOrder(order);
		
		Map<String, String> extra = new HashMap<String, String>();
		String content = "您被安排了订单，目的地：" +order.getDest();
		pushService.notifyWithExtra(order.getDriverId(), "您被安排了一个新订单", content, extra);
		
		msg.put("msg","成功");
		return ResponseEntity.ok(msg);	
	}
	
	@ApiOperation(value = "订单列表", notes = "获取订单列表，分页待做")
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> orderList(@RequestParam String access_token,
			Integer orderStatus){
		Map<String, Object> msg = new HashMap<String, Object>();
		List<HashMap<String, Object>>result = orderService.getOrderList(orderStatus);
		msg.put("data", result);
		return ResponseEntity.ok(msg);	
	}
	
	@ApiOperation(value = "订单详情", notes = "获取订单详情")
	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> orderDetail(@RequestParam String access_token,
														@RequestParam Integer OrderId){
		Map<String, Object> msg = new HashMap<String, Object>();
		HashMap<String, Object>result = orderService.getOrderDetail(OrderId);
		msg.put("data", result);
		return ResponseEntity.ok(msg);	
	}
	
	@ApiOperation(value = "司机接受订单", notes = "司机接受订单")
	@RequestMapping(value = "/acceptOrder", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> acceptOrder(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectOrderByKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.acceptOrder(order))
		{
			Map<String, String> extra = new HashMap<String, String>();
			String content = "司机接受订单，目的地：" +order.getDest();
			System.out.println("OwnerId ="+order.getOwnerId());
			pushService.notifyWithExtra(order.getOwnerId(), "司机接受订单", content, extra);
			
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("data","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}
	
	@ApiOperation(value = "司机开始订单", notes = "司机开始订单")
	@RequestMapping(value = "/start", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> startWorking(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectOrderByKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.startWorking(order))
		{
			Map<String, String> extra = new HashMap<String, String>();
			String content = "司机开始订单，目的地：" +order.getDest();
			pushService.notifyWithExtra(order.getOwnerId(), "司机开始订单", content, extra);
			
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
			@RequestBody ExtraInfoModel info,
			@RequestParam Integer orderId,
			Principal principal) throws Exception{
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectOrderByKey(orderId);
		
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		Car car = carService.selectByPrimaryKey(order.getCarId());

		order.setEndtime(info.getEndtime());
		order.setRemark(info.getRemark());
		order.setExtras(info.getExtras());
		order.setPasscost(info.getParkcost());
		order.setTrafcost(info.getTrafcost());
		order.setParkcost(info.getParkcost());
		
		if(info.getEndtime()!= null)
		{
			GPSService gps = new GPSService();
			Double distance = gps.getWayBillInfo(car.getCarNumber(), order.getStarttime(), order.getEndtime());
			order.setDistance(distance);
		}
		
		if(orderService.pendingForInspection(order))
		{
			Map<String, String> extra = new HashMap<String, String>();
			String content = "司机完成订单，目的地：" +order.getDest();
			pushService.notifyWithExtra(order.getOwnerId(), "司机完成订单", content, extra);
			
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
		Order order = orderService.selectOrderByKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.completeInspection(order))
		{
			Map<String, String> extra = new HashMap<String, String>();
			String content = "订单审核通过，目的地：" +order.getDest();
			pushService.notifyWithExtra(order.getDriverId(), "订单审核通过", content, extra);
			
			//插入考勤数据
			attendenceService.extraAttdCheck(order); 
			
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
		}
		else
		{
			msg.put("msg","订单状态有误：2000"+order.getOrderStatus());
			return ResponseEntity.badRequest().body(msg);
		}
	}
	
	@ApiOperation(value = "秘书处驳回", notes = "秘书处审核驳回")
	@RequestMapping(value = "/rejectOrder", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> rejectOrder(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectOrderByKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		if(orderService.rejectOrder(order))
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
	
	@ApiOperation(value = "司机修改", notes = "司机修改")
	@RequestMapping(value = "/modifyOrder", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> modifyOrder(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			@RequestBody ExtraInfoModel info,
			Principal principal) throws Exception{
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectOrderByKey(orderId);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
		Car car = carService.selectByPrimaryKey(order.getCarId());
		if(info.getEndtime()!= null)
		{
			GPSService gps = new GPSService();
			Double distance = gps.getWayBillInfo(car.getCarNumber(), order.getStarttime(), order.getEndtime());
			order.setDistance(distance);
		}
		order.setEndtime(info.getEndtime());
		order.setRemark(info.getRemark());
		order.setExtras(info.getExtras());
		order.setPasscost(info.getParkcost());
		order.setTrafcost(info.getTrafcost());
		order.setParkcost(info.getParkcost());
		
		if(orderService.modifyOrder(order))
		{
			Map<String, String> extra = new HashMap<String, String>();
			String content = "司机订单报告已修改，目的地：" +order.getDest();
			pushService.notifyWithExtra(order.getOwnerId(), "司机订单报告已修改", content, extra);
			
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
		Order order = orderService.selectOrderByKey(orderId);
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
	
	@ApiOperation(value = "财务处查阅完毕", notes = "财务处查阅完毕")
	@RequestMapping(value = "/test", method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> test(@RequestParam String access_token, 
			@RequestParam Integer orderId,
			Principal principal){
		Map<String, Object> msg = new HashMap<String, Object>();
		Order order = orderService.selectOrderByKey(orderId);
		attendenceService.extraAttdCheck(order);
		if(order == null)
		{
			msg.put("msg","未查询到该订单");
			return ResponseEntity.badRequest().body(msg);
		}
	
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);	
	}
}
