package cs545.waa.project.sellingsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@NotEmpty(message = "*Please provide your street")
	private String street;
	
	@NotEmpty(message = "*Please provide your city")
	private String city;
	
	@NotEmpty(message = "*Please provide your state")
	private String state;
	
	@NotEmpty(message = "*Please provide your zipcode")
	private String zipCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zipCode;
	}

	public void setZip(String zip) {
		this.zipCode = zip;
	}
	
	@Transient
	public String getFull() {
		StringBuilder sb = new StringBuilder();
		sb.append(street);
		sb.append(" ");
		sb.append(city);
		sb.append(" ");
		sb.append(state);
		sb.append(" ");
		sb.append(zipCode);
		
		return sb.toString();
	}

}
