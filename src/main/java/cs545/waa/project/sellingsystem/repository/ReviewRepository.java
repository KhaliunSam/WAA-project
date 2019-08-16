package cs545.waa.project.sellingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cs545.waa.project.sellingsystem.domain.Review;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Integer>{

	@Query(value = "SELECT COUNT(r) FROM Review r WHERE r.status=0")
	long countPendingReview();
	
	@Query(value = "SELECT r FROM Review r WHERE r.status=0")
	List<Review> findPendingReviews();

}
