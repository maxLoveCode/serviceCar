package serviceCar.mapper;

import java.util.HashMap;
import java.util.List;
import serviceCar.config.MyMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pojo.Car;
import pojo.Department;
import pojo.Order;

@Mapper
public interface OrderMapper extends MyMapper<Order>{

	public Integer placeOrder(Order order);
	
	public List<HashMap<String, Object>> getOrderList(@Param("orderStatus")Integer orderStatus);
	
	public List<HashMap<String, Object>> getOrderList(@Param("orderStatus")Integer orderStatus, @Param("keywords")String keywords, @Param("date")String date);
	
	public HashMap<String, Object> getOrderDetail(@Param("OrderId")Integer OrderId);
	
	public List<Department> getDepartmentList();

	public List<Car> getCarList();
	
	public Order selectOrderByKey(@Param("OrderId")Integer OrderId);
}
