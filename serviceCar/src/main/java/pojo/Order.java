package pojo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="sc_order")
public class Order extends Base{
	
	public static final int PENDING = 1;
	public static final int ACCEPTED = 2;
	public static final int WORKING = 3;
	public static final int INSPECTING = 4;
	public static final int INSTPECTED = 5;
	public static final int COMPLETED = 6;

	@Column(name = "driver_id")
	private Integer driverId;
	private Date starttime;
	private Date endtime;

	@Column(name = "unit_price")
	private Double unitPrice;
	private Double total;
	private Float distance;
	
	private String dest;
	private String reason;
	
	@Column(name = "order_status")
	private Integer orderStatus;

	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "car_id")
	private Integer carId;
	
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

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
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
}
