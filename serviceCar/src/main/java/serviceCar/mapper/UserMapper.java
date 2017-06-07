package serviceCar.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pojo.User;
import serviceCar.config.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<User> {
	
	public User findUserByUsername(String username);

	public List<HashMap<String, Object>> getDriverList();
	
	public List<HashMap<String, Object>> getDriverListWithUserType();
	
	public List<HashMap<String, Object>> getUserTypes();
}
