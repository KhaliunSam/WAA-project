package cs545.waa.project.sellingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs545.waa.project.sellingsystem.domain.Review;
import cs545.waa.project.sellingsystem.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public long countPendingReview() {
		return reviewRepository.countPendingReview();
	}

	@Override
	public List<Review> findPendingReviews() {
		return reviewRepository.findPendingReviews();
	}

	@Override
	public Review approveReview(Review review) {
		review.setStatus(1);
		return reviewRepository.save(review);
	}

}
