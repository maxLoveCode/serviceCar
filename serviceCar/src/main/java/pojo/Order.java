package pojo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="sc_order")
public class Order extends Base{

	@Column(name = "driver_id")
	private Integer driverId;
	private Date starttime;
	private Date endtime;

	@Column(name = "unit_price")
	private Double unitPrice;
	private Double total;
	private Double distance;
	
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
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
