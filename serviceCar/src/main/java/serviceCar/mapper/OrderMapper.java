package serviceCar.mapper;

import java.util.List;
import serviceCar.config.MyMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.Order;

@Mapper
public interface OrderMapper extends MyMapper<Order>{

	public Integer placeOrder(Order order);
	
	public List<Order> getOrderList();
}
