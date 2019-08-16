package cs545.waa.project.sellingsystem.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cs545.waa.project.sellingsystem.domain.Address;
import cs545.waa.project.sellingsystem.domain.CreditCard;
import cs545.waa.project.sellingsystem.domain.User;
import cs545.waa.project.sellingsystem.helper.BuyerControllerHelper;
import cs545.waa.project.sellingsystem.service.AddressService;
import cs545.waa.project.sellingsystem.service.OrderService;
import cs545.waa.project.sellingsystem.service.ProductService;
import cs545.waa.project.sellingsystem.service.UserService;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = {"", "/"})
	public String showBuyerHome(Model model) {
		
		User profile = userService.getProfile();
		
		List<User> allActiveSellers = userService.getActiveSellers();
		List<User> followees = profile.getSellers();

		model.addAttribute("buyerId", profile.getId());
		model.addAttribute("email", profile.getEmail());
		model.addAttribute("sellers", BuyerControllerHelper.convert(followees));
		
		model.addAttribute("allSellers", BuyerControllerHelper.convert(allActiveSellers, followees));
		return "buyer";
	}
	
	@GetMapping("/payment")
	public String showCreditCards(Model model) {
		
		User profile = userService.getProfile();

		model.addAttribute("email", profile.getEmail());

		model.addAttribute("cards", profile.getCreditCards());
		return "buyer-cards";
	}
	
	@GetMapping("/payment/add")
	public String showCardForm(@ModelAttribute CreditCard creditCard) {
		
		return "card-form";
	}
	
	@PostMapping("/payment/add")
	public String addCard(@Valid CreditCard creditCard, BindingResult result) {
		
		if(result.hasErrors())
			return "card-form";
		
		userService.addCard(creditCard);
		return "redirect:/buyer/payment";
	}
	
	@GetMapping("/follow")
	public String showAllSeller(Model model) {
		
		User profile = userService.getProfile();
		
		List<User> allActiveSellers = userService.getActiveSellers();

		List<User> followees = profile.getSellers();

		model.addAttribute("buyerId", profile.getId());
		model.addAttribute("email", profile.getEmail());

		model.addAttribute("allSellers", BuyerControllerHelper.convert(allActiveSellers, followees));
		return "buyer-follow";
	}
	
	@GetMapping("/address")
	public String showAddresses(Model model) {
		
		User profile = userService.getProfile();

		model.addAttribute("buyerId", profile.getId());
		model.addAttribute("email", profile.getEmail());

		model.addAttribute("addresses", profile.getShippingAddress());
		return "buyer-address";
	}
	
	@GetMapping("/shopping-cart")
	public String showShoppingCart(Model model) {
		
		User profile = userService.getProfile();

		model.addAttribute("buyerId", profile.getId());
		model.addAttribute("email", profile.getEmail());

		model.addAttribute("cart", profile.getShoppingCart());
		model.addAttribute("addresses", profile.getShippingAddress());
		model.addAttribute("cards", profile.getCreditCards());
		
		return "buyer-shopping-cart";
	}
	
	@ResponseBody
	@DeleteMapping("/delete-address/{addressId}")
	public String deleteAddress(@PathVariable Integer addressId) {
		
		userService.removeAddress(addressId);
		return "Success";
	}
	
	@ResponseBody
	@DeleteMapping("/delete-card/{cardId}")
	public String deleteCard(@PathVariable Integer cardId) {
		
		userService.removeCard(cardId);
		return "Success";
	}
	
	@ResponseBody
	@PostMapping("/shopping-cart/{productId}/{quantity}")
	public String addToCart(@PathVariable Integer productId, @PathVariable Integer quantity) {
		
		userService.addToCart(productId, quantity);
		return "Success";
	}
	
	@GetMapping("/address/add")
	public String showAddressForm(@ModelAttribute Address address) {
		
		return "address-form";
	}
	
	@GetMapping("/happy-shopping/{sellerId}")
	public String showAddressForm(@PathVariable Long sellerId, Model model) {
		
		User profile = userService.getProfile();

		model.addAttribute("buyerId", profile.getId());
		model.addAttribute("email", profile.getEmail());

		model.addAttribute("products", productService.findBySellerId(sellerId));
		return "shopping";
	}
	
	@GetMapping("/address/edit/{addressId}")
	public String showEditAddressForm(@PathVariable Integer addressId, Model model) {
		
		model.addAttribute("address", addressService.findById(addressId));
		return "address-form";
	}
	
	@PostMapping("/address/add")
	public String addAddress(@Valid Address address, BindingResult result) {
		
		if(result.hasErrors())
			return "address-form";
		
		userService.addAddress(address);
		return "redirect:/buyer/address";
	}
	
	@ResponseBody
	@DeleteMapping("/shopping-cart/remove-item/{shoppingCartItemId}")
	public String removeItem(@PathVariable Integer shoppingCartItemId) {
		
		userService.removeItemFromCart(shoppingCartItemId);
		return "Success";
	}
	
	@ResponseBody
	@PostMapping("/order/{shippingAddressId}/{creditCardId}")
	public String placeOrder(@PathVariable Integer shippingAddressId, @PathVariable Integer creditCardId) {
		
		userService.order(shippingAddressId, creditCardId);
		return "Success";
	}
	
	@ResponseBody
	@PutMapping("/follow-seller/{sellerId}/{buyerId}")
	public String followSeller(@PathVariable Long sellerId, @PathVariable Long buyerId) {
		
		userService.followSeller(sellerId, buyerId);
		return "Success";
	}
	
	@ResponseBody
	@PutMapping("/unfollow-seller/{sellerId}/{buyerId}")
	public String unfollowSeller(@PathVariable Long sellerId, @PathVariable Long buyerId) {
		
		userService.unfollowSeller(sellerId, buyerId);
		return "Success";
	}
	
	@GetMapping("/history1")
	public String showOrderHistory(Model model) {
		
		User profile = userService.getProfile();

		model.addAttribute("buyerId", profile.getId());
		model.addAttribute("email", profile.getEmail());

		model.addAttribute("orders", orderService.findOrdersByBuyerId(profile.getId()));
		return "buyer-order-history";
	}

}
