package cs545.waa.project.sellingsystem.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs545.waa.project.sellingsystem.domain.Product;
import cs545.waa.project.sellingsystem.domain.User;
import cs545.waa.project.sellingsystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Product update(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product findById(Integer productId) {
		
		return productRepository.findById(productId).get();
	}

	@Override
	public Product updatePrice(Integer productId, Double price) {
		Product product = productRepository.findById(productId).get();
		product.setPrice(price);
		return productRepository.save(product);
	}

	public void addProduct(Product product, User seller) {
		product.setActive(1);
		product.setSeller(seller);
		productRepository.save(product);
		
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = productRepository.findById(productId).get();
		product.setActive(0);
		productRepository.save(product);
	}

	@Override
	public List<Product> findBySellerId(Long sellerId) {
		
		User seller = userRepository.findById(sellerId).get();
		return seller.getProducts();
	}
	
	

	
	

}
