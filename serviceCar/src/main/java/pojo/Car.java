package pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sc_car")
public class Car extends Base {

	@Column(name = "car_number")
	private String carNumber;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

}
