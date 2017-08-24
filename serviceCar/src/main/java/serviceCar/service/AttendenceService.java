package serviceCar.service;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Order;
import pojo.Overtime;

@Service
public class AttendenceService {
	public static final Integer startHourStd = 8;
	public static final Integer startMinStd = 30;
	
	public static final Integer endHourStd = 17;
	public static final Integer endMinStd = 30;
	

	@Autowired
	OvertimeService overtimeService;
	
	public void extraAttdCheck(Order order)
	{
		DateTime startDate = new DateTime(order.getStarttime());
		DateTime endDate = new DateTime(order.getEndtime());
		
		double extraMorning = this.compareTime(startDate, startDate, startHourStd, startMinStd, 1);
		double extraEvening = this.compareTime(startDate, endDate, endHourStd, endMinStd, 2);
		
		if(extraMorning!=0 || extraEvening!=0)
		{
			Overtime overtime = overtimeService.selectByOrderId(order.getId());
			if(overtime == null)
			{
				overtime = new Overtime();
				overtime.setEveningExtra(extraEvening);
				overtime.setMorningExtra(extraMorning);
				overtime.setOrderId(order.getId());
				overtime.setDriverId(order.getDriverId());
				overtimeService.insertSelective(overtime);
			}
			else
			{
				overtime.setEveningExtra(extraEvening);
				overtime.setMorningExtra(extraMorning);
				overtime.setOrderId(order.getId());
				overtime.setDriverId(order.getDriverId());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
				overtimeService.updateByPrimaryKeySelective(overtime);			
			}
		}
		
		System.out.println(extraMorning);
		System.out.println(extraEvening);
	}
	
	private double compareTime(DateTime start,DateTime time, Integer Std1, Integer Std2, Integer Mode)
	{
		double extras = 0.0;  
		DateTime Std = new DateTime(start.getYear(),start.getMonthOfYear(),start.getDayOfMonth(),Std1,Std2,0,0).withZone(start.getZone());
		if(Mode == 1)
		{
			extras = (double)(Std.getMillis()-time.getMillis())/(1000*60*60);
		}
		else
		{
			extras = (double)(time.getMillis()-Std.getMillis())/(1000*60*60);
		}
		return (extras>0?extras:0);
	}

}
