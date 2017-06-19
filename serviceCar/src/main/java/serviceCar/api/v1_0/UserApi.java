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

import pojo.Notify;
import pojo.User;
import serviceCar.service.UserService;
import serviceCar.service.UtilService;
import io.swagger.annotations.ApiOperation;

@RestController("UserApi")
@RequestMapping("/v1_0")

public class UserApi {

	@Autowired
	UserService userService;
	@Autowired
	UtilService utilService;

	@ApiOperation(value = "使用 token获取个人信息", 
			notes = "")
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public ResponseEntity<?> getUserInfo(@RequestParam String access_token,
											Principal principal)
	{
		Map<String, Object> msg = new HashMap<String, Object>();
		String name = principal.getName();
		User user = userService.findUserByUsername(name);
		if(user != null)
		{
			msg.put("data",user);
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	}
	
	@ApiOperation(value = "修改密码", 
			notes = "")
	@RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
	public ResponseEntity<?> changePassword(@RequestParam String access_token,
											@RequestParam String newPass,
											Principal principal)
	{
		Map<String, Object> msg = new HashMap<String, Object>();
		String name = principal.getName();
		User user = userService.findUserByUsername(name);
		if(user != null)
		{
			user.setPassword(newPass);
			userService.changePassword(user);
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	}
	
	@ApiOperation(value = "获取消息", 
			notes = "")
	@RequestMapping(value = "/getNotifyList", method = RequestMethod.GET)
	public ResponseEntity<?> getNotifyList(@RequestParam String access_token,
											Principal principal)
	{
		Map<String, Object> msg = new HashMap<String, Object>();
		String name = principal.getName();
		User user = userService.findUserByUsername(name);
		if(user != null)
		{
			List<Notify> data = utilService.getNotify(user.getId());
			msg.put("data", data);
			msg.put("msg","成功");
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	}
}
