package cs545.waa.project.sellingsystem.service;

import java.util.List;

import cs545.waa.project.sellingsystem.domain.Review;

public interface ReviewService {
	
	long countPendingReview();
	
	List<Review> findPendingReviews();
	
	Review approveReview(Review review);

}
