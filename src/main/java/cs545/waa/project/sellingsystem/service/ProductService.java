package cs545.waa.project.sellingsystem.service;

import java.util.List;

import cs545.waa.project.sellingsystem.domain.Product;
import cs545.waa.project.sellingsystem.domain.User;

public interface ProductService {
	
	Product update(Product product);
	
	Product findById(Integer productId);
	
	Product updatePrice(Integer productId, Double price);
	
	void addProduct(Product product, User user);

	void deleteProduct(Integer productId);

	List<Product> findBySellerId(Long sellerId);

}
