package cs545.waa.project.sellingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cs545.waa.project.sellingsystem.domain.Review;
import cs545.waa.project.sellingsystem.service.ReviewService;
import cs545.waa.project.sellingsystem.service.UserService;

@Controller
@RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.PUT})
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value = {"/", ""})
	private String showAdminPanel(Model model) {
		model.addAttribute("sellerRequests", userService.countPendingSellers());
		model.addAttribute("reviewRequests", reviewService.countPendingReview());
		return "admin-panel";
	}
	
	@GetMapping("/pending-sellers")
	public String showPendingSellers(Model model) {
		model.addAttribute("sellers", userService.findPendingSellers());
		return "pending-sellers";
	}
	
	@GetMapping("/pending-reviews")
	public String showPendingReviews(Model model) {
		model.addAttribute("reviews", reviewService.findPendingReviews());
		return "pending-reviews";
	}
	
	
	@ResponseBody
	@PutMapping("/approve-seller/{sellerId}")
	public String approveSellers(@PathVariable Long sellerId) {
		userService.approveSeller(sellerId);
		return "success";
	}

	@ResponseBody
	@PutMapping("/approve-review")
	public String approveReviews(@RequestBody Review review, Model model) {
		model.addAttribute("reviewList", reviewService.approveReview(review));
		return "success";
	}

	
}
