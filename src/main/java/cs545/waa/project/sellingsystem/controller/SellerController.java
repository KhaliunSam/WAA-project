package cs545.waa.project.sellingsystem.controller;


import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

import cs545.waa.project.sellingsystem.domain.Product;
import cs545.waa.project.sellingsystem.domain.User;
import cs545.waa.project.sellingsystem.service.ProductService;
import cs545.waa.project.sellingsystem.service.UserService;

@Controller
@RequestMapping("/seller")
public class SellerController {	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"", "/"})
	public String showSellerHome(Model model) {
		
		User profile = userService.getProfile();
		List<Product> products = profile.getProducts();
		model.addAttribute("products", products);
		return "seller";
	}
	
	@GetMapping("/product/{productId}")
    public String getProductDetailById(@PathVariable Integer productId, Model model){
		
        model.addAttribute("product", productService.findById(productId));
        return "product-detail";
    }
	
	@GetMapping("/product/add")
    public String showProductForm(@ModelAttribute Product product, Model model) {
		
        return "product-form";
    } 
	
	@PostMapping("/product/add")
	public String addProduct(@Valid Product product, BindingResult result) {
		
		if(result.hasErrors()) {
			return "product-form";
		}
		productService.addProduct(product, userService.getProfile());
		return "redirect:/seller";
	}
	
	@ResponseBody
	@DeleteMapping("/product/delete/{productId}")
	public String removeProduct(@PathVariable Integer productId) {
		
		productService.deleteProduct(productId);
		return "Success";
	}
	
	
	@GetMapping("/product/edit/{productId}")
	public String showEditProductForm(@PathVariable Integer productId, Model model) {
		
		model.addAttribute("product", productService.findById(productId));
		return "product-form";
	}
	
	
}
