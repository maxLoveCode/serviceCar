package serviceCar.service;

import java.util.HashMap;
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
	
	public Boolean acceptOrder(Order order)
	{
		if(order.getOrderStatus() != Order.PENDING)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.ACCEPTED);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	public Boolean startWorking(Order order)
	{
		if(order.getOrderStatus() != Order.ACCEPTED)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.WORKING);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	public Boolean pendingForInspection(Order order)
	{
		if(order.getOrderStatus() != Order.WORKING)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.INSPECTING);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	public Boolean completeInspection(Order order)
	{
		if(order.getOrderStatus() != Order.INSPECTING)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.INSTPECTED);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	public Boolean completeOrder(Order order)
	{
		if(order.getOrderStatus() != Order.INSTPECTED)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.COMPLETED);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	public List<HashMap<String, Object>> getOrderList()
	{
		return orderMapper.getOrderList();
	}
}
