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
import serviceCar.dto.BaseCondition;
import serviceCar.dto.Message;
import pojo.Car;
import serviceCar.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController{
	
	@Autowired
	CarService carService;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String carList(){
		return "car/index";
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
		Page<Car> pager = PageHelper.startPage(condition.getPage(), condition.getRows());
		List<Car> list = carService.showList(condition.getKeywords());
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
	public List<Car> selectList(String q) {
		return carService.showList(q);
	}
	
	/**
	 * 新增操作
	 * 
	 * @param car
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Message add(Car car){
		carService.insertSelective(car);
		if (car.getId() != 0) {
			
		} else {
			return failMessage("新增车辆失败");
		}
		return successMessage();
	}
	
	/**
	 * 更新操作
	 * 
	 * @param car
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Message update(Car car){
		carService.updateByPrimaryKeySelective(car);
		return successMessage();
	}
	
	/**
	 * 删除操作
	 * 
	 * @param car
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Message delete(Car car){
		carService.deleteByPrimaryKey(car);
		return successMessage();
	}
	
}
