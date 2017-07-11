package serviceCar.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
		//properties initialization
		order.setOrderStatus(1);
		order.setUnitPrice(3.0);
		return this.insertSelective(order);
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
		if(order.getOrderStatus() != Order.INSPECTING
				&& order.getOrderStatus() != Order.REJECTED

				&& order.getOrderStatus() != Order.MODIFIED)
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
	
	public Boolean rejectOrder(Order order)
	{
		if(order.getOrderStatus() != Order.INSPECTING && 
				order.getOrderStatus() != Order.MODIFIED)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.REJECTED);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	
	public Boolean modifyOrder(Order order)
	{
		if(order.getOrderStatus() != Order.REJECTED)
		{
			return false;
		}
		else
		{
			order.setOrderStatus(Order.MODIFIED);
			this.updateByPrimaryKeySelective(order);
			return true;
		}
	}
	
	public List<HashMap<String, Object>> getOrderList(Integer orderStatus)
	{
		return orderMapper.getOrderList(orderStatus,null,null);
	}
	
	public List<HashMap<String, Object>> getOrderList(Integer orderStatus,String keywords,String date)
	{
		return orderMapper.getOrderList(orderStatus,keywords,date);
	}
	
	public HashMap<String, Object> getOrderDetail(Integer OrderId)
	{
		return orderMapper.getOrderDetail(OrderId);
	}
	

	public Order selectOrderByKey(@Param("OrderId")Integer OrderId)
	{
		return orderMapper.selectOrderByKey(OrderId);
	}
}
