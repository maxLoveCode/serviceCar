package serviceCar.dto;

import java.util.Date;


public class ExtraInfoModel {
	
	private Date endtime;
    
    private Double passcost;
    
    private Double parkcost;
    
    private Double trafcost;
    
    private Double extras;
    
    private String remark;

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Double getPasscost() {
		return passcost;
	}

	public void setPasscost(Double passcost) {
		this.passcost = passcost;
	}

	public Double getParkcost() {
		return parkcost;
	}

	public void setParkcost(Double parkcost) {
		this.parkcost = parkcost;
	}

	public Double getTrafcost() {
		return trafcost;
	}

	public void setTrafcost(Double trafcost) {
		this.trafcost = trafcost;
	}

	public Double getExtras() {
		return extras;
	}

	public void setExtras(Double extras) {
		this.extras = extras;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
