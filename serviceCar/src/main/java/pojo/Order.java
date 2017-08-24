package pojo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="sc_order")
public class Order extends Base{
	
	public static final int PENDING = 1;
	public static final int ACCEPTED = 2;
	public static final int WORKING = 3;
	public static final int INSPECTING = 4;
	public static final int INSTPECTED = 5;
	public static final int COMPLETED = 6;
	public static final int REJECTED = 7;
	public static final int MODIFIED = 8;

	@Column(name = "driver_id")
	private Integer driverId;
	
	@Column(name = "owner_id")
	private Integer ownerId;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
	private Date starttime;
    
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
	private Date endtime;

	@Column(name = "unit_price")
	private Double unitPrice;
	private Double total;
	private Double passcost;
	private Double parkcost;
	private Double trafcost;
	private Double extras;
	
	private Double distance;
	
	private String dest;
	private String orig;
	
	private String reason;
	
	@Column(name = "order_status")
	private Integer orderStatus;

	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "car_id")
	private Integer carId;
	
	private String remark;
	
	
	public Order(Integer orderId){
		this.setId(orderId);
	}
	
	public Order() {
	}

	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}
}
