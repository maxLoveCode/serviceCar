package serviceCar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Car;
import pojo.Department;
import pojo.User;
import serviceCar.mapper.OrderMapper;

@Service
public class UtilService {
	@Autowired
	OrderMapper orderMapper;
	
	public List<Department> getDepartmentList()
	{
		return orderMapper.getDepartmentList();
	}
	
	public List<Car> getCarList()
	{
		return orderMapper.getCarList();
	}
}
