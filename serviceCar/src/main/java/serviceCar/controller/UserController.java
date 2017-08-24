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

import serviceCar.dto.Message;

import serviceCar.dto.BaseCondition;

import pojo.User;
import serviceCar.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "user/index";
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
		List<HashMap<String, Object>> list = userService.getDriverListWithUserType();
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", pager.getTotal());
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 新增操作
	 * 
	 * @param admin
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Message add(User user) {
		User check = userService.findUserByUsername(user.getUsername());
		if (check != null) {
			return failMessage("用户名已存在");
		}
		userService.insertSelective(user);
		return successMessage();
	}
	
	/**
	 * 更新操作
	 * 
	 * @param dest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Message update(User dest) {
		User admin = userService.selectByPrimaryKey(dest.getId());
		//如修改了用户名，检查修改后的用户名是否已存在
		if(!admin.getUsername().equals(dest.getUsername())){
			User check = userService.findUserByUsername(dest.getUsername());
			if(check != null) {
				return failMessage("用户名已存在");
			}
			admin.setUsername(dest.getUsername());
		}
		admin.setUsertype(dest.getUsertype());
		userService.updateByPrimaryKey(admin);
		return successMessage();
	}
	
	/**
	 * 删除操作
	 * 
	 * @param admin
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Message delete(User dest) {
		userService.deleteByPrimaryKey(dest.getId());
		return successMessage();
	}
	
	@RequestMapping(value = "/attendenceIndex", method = RequestMethod.GET)
	public String attendenceIndex() {
		return "user/attendenceIndex";
	}
	
	/**
	 * 获得列表数据
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/attendenceList")
	public Map<String,Object> attendenceList(BaseCondition condition) {
		Page<User> pager = PageHelper.startPage(condition.getPage(), condition.getRows());// 分页类
		List<HashMap<String, Object>> list = userService.getAttendenceList();
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", pager.getTotal());
		result.put("rows", list);
		return result;
	}
}
