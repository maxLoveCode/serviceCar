package pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="sc_overtime")
public class Overtime {

	@Column(name = "driver_id")
	private Integer driverId;
	
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "morningExtra")
	private Double morningExtra;
	@Column(name = "eveningExtra")
	private Double eveningExtra;
	
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getMorningExtra() {
		return morningExtra;
	}
	public void setMorningExtra(Double morningExtra) {
		this.morningExtra = morningExtra;
	}
	public Double getEveningExtra() {
		return eveningExtra;
	}
	public void setEveningExtra(Double eveningExtra) {
		this.eveningExtra = eveningExtra;
	}
}
