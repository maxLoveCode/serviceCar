package serviceCar.mapper;

import org.apache.ibatis.annotations.Mapper;

import pojo.Overtime;
import serviceCar.config.MyMapper;

@Mapper
public interface OvertimeMapper extends MyMapper<Overtime>{

	Overtime selectByOrderId(Integer orderId);
}
