package serviceCar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceCar.mapper.DepartmentMapper;
import pojo.Department;

@Service
public class DepartmentService extends BaseService<Department> {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	public List<Department> showList(String keywords){
		return departmentMapper.showList(keywords);
	}
}
