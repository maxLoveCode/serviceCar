package serviceCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Overtime;
import serviceCar.mapper.OvertimeMapper;
@Service
public class OvertimeService extends BaseService<Overtime>{

	@Autowired
	OvertimeMapper overtimeMapper;

	public Overtime selectByOrderId(Integer id) {
		return overtimeMapper.selectByOrderId(id);
	}
}
