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

import pojo.Department;
import serviceCar.dto.BaseCondition;
import serviceCar.dto.Message;
import serviceCar.service.DepartmentService;


@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
	@Autowired
	DepartmentService departmentService;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String departmentList(){
		return "department/index";
	}
	
	/**
	 * 获得列表数据
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Map<String, Object> list(BaseCondition condition) {
		Page<Department> pager = PageHelper.startPage(condition.getPage(), condition.getRows());
		List<Department> list = departmentService.showList(condition.getKeywords());
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
	@RequestMapping(value = "/selectList")
	public List<Department> selectList(String q) {
		return departmentService.showList(q);
	}
	
	/**
	 * 新增操作
	 * 
	 * @param department
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Message add(Department department){
		departmentService.insertSelective(department);
		if (department.getId() != 0) {
			
		} else {
			return failMessage("新增车辆失败");
		}
		return successMessage();
	}
	
	/**
	 * 更新操作
	 * 
	 * @param department
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Message update(Department department){
		departmentService.updateByPrimaryKeySelective(department);
		return successMessage();
	}
	
	/**
	 * 删除操作
	 * 
	 * @param department
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Message delete(Department department){
		departmentService.deleteByPrimaryKey(department);
		return successMessage();
	}
	
}
