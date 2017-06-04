package pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class Base {

	@Id
	private Integer id;
	@Column(name = "createtime")
	private Date createtime;
	@Column(name = "updatetime")
	private Date updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
