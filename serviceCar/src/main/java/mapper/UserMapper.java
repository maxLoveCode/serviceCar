package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import config.MyMapper;
import pojo.User;

@Mapper
public interface UserMapper extends MyMapper<User> {
	
	public User findUserByUsername(String username);
}
