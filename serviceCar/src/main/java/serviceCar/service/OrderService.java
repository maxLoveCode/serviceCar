package serviceCar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceCar.mapper.OrderMapper;
import pojo.Order;

@Service
public class OrderService extends BaseService<Order> {
	
	@Autowired
	OrderMapper orderMapper;
	

	public Integer placeOrder(Order order)
	{
		return orderMapper.insert(order);
	}
	
	public List<Order >getOrderList()
	{
		return orderMapper.getOrderList();
	}
}
