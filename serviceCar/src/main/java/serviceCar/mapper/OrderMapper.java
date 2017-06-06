package serviceCar.mapper;

import java.util.HashMap;
import java.util.List;
import serviceCar.config.MyMapper;

import org.apache.ibatis.annotations.Mapper;

import pojo.Car;
import pojo.Department;
import pojo.Order;

@Mapper
public interface OrderMapper extends MyMapper<Order>{

	public Integer placeOrder(Order order);
	
	public List<HashMap<String, Object>> getOrderList();
	
	public List<Department> getDepartmentList();

	public List<Car> getCarList();
}
