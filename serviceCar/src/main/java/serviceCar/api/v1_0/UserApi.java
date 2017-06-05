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

import pojo.User;
import serviceCar.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController("UserApi")
@RequestMapping("/v1_0")

public class UserApi {

	@Autowired
	UserService userService;

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
			msg.put("user",user);
			return ResponseEntity.ok(msg);
		}
		msg.put("msg","未找到该用户");
		return ResponseEntity.badRequest().body(msg);
	}   
}
