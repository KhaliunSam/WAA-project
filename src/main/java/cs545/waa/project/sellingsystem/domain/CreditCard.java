package cs545.waa.project.sellingsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
public class CreditCard {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(min = 16, max = 16, message = "*Please provide 16 digit card number")
	private String cardNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Transient
	public String getMaskedNumber() {
		if(cardNumber == null || cardNumber.length() < 16)
			return "";
		return "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length() - 4);
	}
}
