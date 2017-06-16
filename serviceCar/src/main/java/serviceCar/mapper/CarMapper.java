package serviceCar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import serviceCar.config.MyMapper;
import pojo.Car;

@Mapper
public interface CarMapper extends MyMapper<Car>{
	
	List<Car> showList(@Param("keywords") String keywords);
}
