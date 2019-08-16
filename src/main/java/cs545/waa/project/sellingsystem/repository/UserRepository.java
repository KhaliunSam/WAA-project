package cs545.waa.project.sellingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cs545.waa.project.sellingsystem.domain.Review;
import cs545.waa.project.sellingsystem.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	@Query(value = "SELECT COUNT(u) FROM User u WHERE u.status=0 AND u.role.role = 'ROLE_SELLER'")
	long countPendingSellers();

	@Query(value = "SELECT u FROM User u WHERE u.status=0 AND u.role.role = 'ROLE_SELLER'")
	List<User> findPendingSellers();

	@Query(value = "SELECT u FROM User u WHERE u.status=1 AND u.role.role = 'ROLE_SELLER'")
	List<User> findActiveSellers();
	
}
