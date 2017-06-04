package service; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMapper;
import pojo.User;

@Service
public class UserService extends BaseService<User>{
	
	@Autowired
	UserMapper userMapper;
	

	public User findUserByUsername(String username)
	{
		return userMapper.findUserByUsername(username);
	}
}
