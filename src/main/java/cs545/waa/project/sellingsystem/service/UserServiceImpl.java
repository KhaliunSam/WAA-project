package cs545.waa.project.sellingsystem.service;

import cs545.waa.project.sellingsystem.repository.AddressRepository;
import cs545.waa.project.sellingsystem.repository.CreditCardRepository;
import cs545.waa.project.sellingsystem.repository.OrderRepository;
import cs545.waa.project.sellingsystem.repository.ProductRepository;
import cs545.waa.project.sellingsystem.repository.UserRepository;
import cs545.waa.project.sellingsystem.domain.Address;
import cs545.waa.project.sellingsystem.domain.CreditCard;
import cs545.waa.project.sellingsystem.domain.Order;
import cs545.waa.project.sellingsystem.domain.OrderItem;
import cs545.waa.project.sellingsystem.domain.Product;
import cs545.waa.project.sellingsystem.domain.ShoppingCartItem;
import cs545.waa.project.sellingsystem.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User createUser(User user) {

		if (user.getRole().getRole().equalsIgnoreCase("ROLE_BUYER")) {
			user.setPoint(0);
			user.setStatus(1);
			user.setProducts(null);
			user.setShippingAddress(new ArrayList<>());
			user.setSellers(new ArrayList<>());
			user.setBuyers(null);
		} else {
			user.setStatus(0);
			user.setProducts(new ArrayList<>());
			user.setBuyers(new ArrayList<>());
			user.setSellers(null);
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<User> findPendingSellers() {
		return userRepository.findPendingSellers();
	}

	@Override
	public User approveSeller(Long sellerId) {

		User seller = userRepository.findById(sellerId).get();
		seller.setStatus(1);
		return userRepository.save(seller);
	}

	@Override
	public long countPendingSellers() {
		return userRepository.countPendingSellers();
	}

	@Override
	public User getProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		return findUserByEmail(email);
	}

	@Override
	public List<User> getActiveSellers() {

		return userRepository.findActiveSellers();
	}

	@Override
	public User findUserById(Long id) {

		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public User followSeller(Long sellerId, Long buyerId) {
		User seller = findUserById(sellerId);
		User buyer = findUserById(buyerId);

		buyer.getSellers().add(seller);
		return userRepository.save(buyer);
	}

	@Override
	public User unfollowSeller(Long sellerId, Long buyerId) {
		User buyer = findUserById(buyerId);

		List<User> sellers = new ArrayList<>();

		for (User seller : buyer.getSellers())
			if (seller.getId() != sellerId)
				sellers.add(seller);

		buyer.setSellers(sellers);
		return userRepository.save(buyer);
	}

	@Override
	public void removeAddress(Integer addressId) {
		User profile = getProfile();
		List<Address> addresses = new ArrayList<>();
		for (Address a : profile.getShippingAddress()) {
			if (a.getId() != addressId)
				addresses.add(a);
		}
		profile.setShippingAddress(addresses);
		userRepository.save(profile);
	}

	@Override
	public User addAddress(Address address) {

		if (address.getId() == null) {
			User buyer = getProfile();
			buyer.getShippingAddress().add(address);
			return userRepository.save(buyer);
		}
		addressRepository.save(address);
		return getProfile();
	}

	@Override
	public User addCard(CreditCard creditCard) {
		User buyer = getProfile();
		buyer.getCreditCards().add(creditCard);
		return userRepository.save(buyer);
	}

	@Override
	public void removeCard(Integer cardId) {
		User profile = getProfile();
		List<CreditCard> cards = new ArrayList<>();
		for(CreditCard cc : profile.getCreditCards()) {
			if(cc.getId() != cardId)
				cards.add(cc);
		}
		profile.setCreditCards(cards);
		userRepository.save(profile);
		
	}

	@Override
	public void addToCart(Integer productId, Integer quantity) {
		
		ShoppingCartItem sci = new ShoppingCartItem();
		sci.setProduct(productRepository.findById(productId).get());
		sci.setQuantity(quantity);
		
		User buyer = getProfile();
		buyer.getShoppingCart().add(sci);
		
		userRepository.save(buyer);
	}

	@Override
	public void removeItemFromCart(Integer shoppingCartItemId) {
		
		User profile = getProfile();
		List<ShoppingCartItem> shoppingCart = new ArrayList<>();
		
		for(ShoppingCartItem sci : profile.getShoppingCart()) {
			if(sci.getId() != shoppingCartItemId)
				shoppingCart.add(sci);
		}
		profile.setShoppingCart(shoppingCart);
		userRepository.save(profile);
	}

	@Override
	public void order(Integer shippingAddressId, Integer creditCardId) {
		
		User profile = getProfile();
		
		Order order = new Order();
		order.setUserId(profile.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(ShoppingCartItem sci : profile.getShoppingCart()) {
			OrderItem oi = new OrderItem();
			oi.setPrice(sci.getProduct().getPrice());
			oi.setProduct(sci.getProduct());
			oi.setQuantity(sci.getQuantity());
			oi.setStatus("PAID");
			
			orderItems.add(oi);
		}
		
		order.setItems(orderItems);
		order.setOrderedDate(LocalDate.now());
		order.setShippingAddress(addressRepository.findById(shippingAddressId).get().getFull());
		order.setCardNumber(creditCardRepository.findById(creditCardId).get().getMaskedNumber());
		
		orderRepository.save(order);
	}

}
