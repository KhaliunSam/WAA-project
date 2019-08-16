package cs545.waa.project.sellingsystem.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shopping_cart_id")
    private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ShoppingCartItem> cartItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ShoppingCartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<ShoppingCartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
