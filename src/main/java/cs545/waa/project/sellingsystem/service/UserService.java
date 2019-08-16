package cs545.waa.project.sellingsystem.service;

import java.util.List;

import cs545.waa.project.sellingsystem.domain.Address;
import cs545.waa.project.sellingsystem.domain.CreditCard;
import cs545.waa.project.sellingsystem.domain.User;

public interface UserService {
	
	User findUserByEmail(String email);
	
	User createUser(User user);
	
	long countPendingSellers();
	
	List<User> findPendingSellers();

	User approveSeller(Long sellerId);
	
	User getProfile();

	List<User> getActiveSellers();

	User findUserById(Long sellerId);

	User updateUser(User buyer);

	User followSeller(Long sellerId, Long buyerId);
	
	User unfollowSeller(Long sellerId, Long buyerId);

	void removeAddress(Integer addressId);

	User addAddress(Address address);
	
	User addCard(CreditCard address);

	void removeCard(Integer cardId);

	void addToCart(Integer productId, Integer quantity);

	void removeItemFromCart(Integer shoppingCartItemId);

	void order(Integer shippingAddressId, Integer creditCardId);
}
