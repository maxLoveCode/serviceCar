package serviceCar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Car;
import pojo.Department;
import pojo.Notify;
import pojo.User;
import serviceCar.mapper.OrderMapper;
import serviceCar.mapper.UserMapper;

@Service
public class UtilService {
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	UserMapper userMapper;
	
	public List<Department> getDepartmentList()
	{
		return orderMapper.getDepartmentList();
	}
	
	public List<Car> getCarList()
	{
		return orderMapper.getCarList();
	}
	
	public List<Notify> getNotify(Integer userId)
	{
		return userMapper.getNotify(userId);
	}
}
