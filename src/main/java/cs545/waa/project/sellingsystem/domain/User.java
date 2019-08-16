package cs545.waa.project.sellingsystem.domain;

import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Length(min = 5, message = "*Your password must have at least 5 characters")
	private String password;

	@Column(name = "first_name")
	@NotEmpty(message = "*Please provide your first name")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	private Integer status;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull(message = "*Please choose a role")
	private Role role;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> shippingAddress;

	private Integer point;

	@ManyToMany
	private List<User> sellers;

	@ManyToMany(mappedBy = "sellers", cascade = CascadeType.ALL)
	private List<User> buyers;
	
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Product> products;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CreditCard> creditCards;

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ShoppingCartItem> shoppingCart;
	
	public List<ShoppingCartItem> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<ShoppingCartItem> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Product> getProducts() {
		if(products == null) return null;
		
		return products.stream().filter(p -> p.getActive() == 1).collect(Collectors.toList());
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Address> getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(List<Address> shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<User> getSellers() {
		return sellers;
	}

	public void setSellers(List<User> sellers) {
		this.sellers = sellers;
	}

	public List<User> getBuyers() {
		return buyers;
	}

	public void setBuyers(List<User> buyers) {
		this.buyers = buyers;
	}

}