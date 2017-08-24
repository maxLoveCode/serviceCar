package serviceCar.service; 

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.User;
import serviceCar.mapper.UserMapper;

@Service
public class UserService extends BaseService<User>{
	
	@Autowired
	UserMapper userMapper;
	

	public User findUserByUsername(String username)
	{
		return userMapper.findUserByUsername(username);
	}
	
	public List<HashMap<String, Object>> getDriverList()
	{
		return userMapper.getDriverList();
	}
	

	public List<HashMap<String, Object>> getDriverListWithUserType()
	{
		return userMapper.getDriverListWithUserType();
	}
	

	public List<HashMap<String, Object>> getUserTypes()
	{
		return userMapper.getUserTypes();
	}
	
	public int changePassword(User user)
	{
		return userMapper.updateByPrimaryKeySelective(user);
	}

	public List<HashMap<String, Object>> getAttendenceList() {
		return userMapper.getAttendenceList();
	}
}
