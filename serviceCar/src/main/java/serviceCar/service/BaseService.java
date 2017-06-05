package serviceCar.service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import serviceCar.config.MyMapper;

public abstract class BaseService<T> {

	@Autowired
	protected MyMapper<T> mapper;

	/**
	 * 动态增加(获得ID)
	 * 
	 * @param entity
	 * @return
	 */ 
	public int insertSelective(T record) {
		setCreatetime(record);
		return mapper.insertUseGeneratedKeys(record);
	}

	/**
	 * 动态更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByPrimaryKeySelective(T record) {
		setUpdatetime(record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 正常更新(更新全部)
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByPrimaryKey(T record) {
		setUpdatetime(record);
		return mapper.updateByPrimaryKey(record);
	}

	/**
	 * 真删除
	 * 
	 * @param entity
	 * @return
	 */
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * 根据ID获得实体
	 * 
	 * @param key
	 * @return
	 */
	public T selectByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * 根据条件获得实体列表
	 * 
	 * @param record
	 * @return
	 */
	public List<T> select(T record) {
		return mapper.select(record);
	}

	/**
	 * 根据条件获得一个实体
	 * 
	 * @param record
	 * @return
	 */
	public T selectOne(T record) {
		return mapper.selectOne(record);
	}

	/**
	 * 根据条件获得实体数量
	 * 
	 * @param record
	 * @return
	 */
	public int selectCount(T record) {
		return mapper.selectCount(record);
	}

	/**
	 * 设置默认创建时间
	 * @param record
	 */
	private void setCreatetime(T record) {
		try {
			Method method = record.getClass().getMethod("setCreatetime", Date.class);
			method.invoke(record, new Date());
		} catch (Exception e) {
		}
	}

	/**
	 * 设置默认更新时间
	 * @param record
	 */
	private void setUpdatetime(T record) {
		try {
			Method method = record.getClass().getMethod("setUpdatetime", Date.class);
			method.invoke(record, new Date());
		} catch (Exception e) {
		}
	}
	
	public void softDelete(T record){
		try {
			Method method = record.getClass().getMethod("setDelFlag", Integer.class);
			method.invoke(record, 0);
			updateByPrimaryKey(record);
		}
		catch (Exception e){
			
		}
	}
}
