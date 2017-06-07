package serviceCar.api.v1_0;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pojo.Car;
import pojo.Department;
import pojo.User;
import serviceCar.service.UserService;
import serviceCar.service.UtilService;

@RestController("UtilApi")
@RequestMapping("/v1_0")

public class UtilApi {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UtilService utilService;

	@ApiOperation(value = "获得司机列表", 
			notes = "获得司机列表")
	@RequestMapping(value = "/getDriverList", method = RequestMethod.GET)
	public ResponseEntity<?> getDriverList(@RequestParam String access_token,
											Principal principal)
	{
		Map<String, Object> msg = new HashMap<String, Object>();
		List<HashMap<String, Object>> user = userService.getDriverList();
		if(user != null)
		{
			msg.put("data",user);
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	} 
	
	@ApiOperation(value = "获得部门列表", 
			notes = "获得部门列表")
	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET)
	public ResponseEntity<?> getDepartmentList(@RequestParam String access_token,
											Principal principal)
	{
		Map<String, Object> msg = new HashMap<String, Object>();
		List<Department> list = utilService.getDepartmentList();
		if(list != null)
		{
			msg.put("data",list);
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	} 
	
	@ApiOperation(value = "获得车辆列表", 
			notes = "获得车辆列表")
	@RequestMapping(value = "/getCarList", method = RequestMethod.GET)
	public ResponseEntity<?> getCarList(@RequestParam String access_token,
											Principal principal)
	{
		Map<String, Object> msg = new HashMap<String, Object>();
		List<Car> list = utilService.getCarList();
		if(list != null)
		{
			msg.put("data",list);
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	} 
}
