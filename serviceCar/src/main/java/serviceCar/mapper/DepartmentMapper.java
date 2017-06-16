package serviceCar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import serviceCar.config.MyMapper;
import pojo.Department;

@Mapper
public interface DepartmentMapper extends MyMapper<Department>{
	
	List<Department> showList(@Param("keywords") String keywords);
}
