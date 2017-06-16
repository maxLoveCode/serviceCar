package serviceCar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceCar.mapper.CarMapper;
import pojo.Car;

@Service
public class CarService extends BaseService<Car> {
	
	@Autowired
	CarMapper carMapper;
	
	public List<Car> showList(String keywords){
		return carMapper.showList(keywords);
	}
}
