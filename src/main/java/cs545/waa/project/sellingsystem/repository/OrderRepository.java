package cs545.waa.project.sellingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cs545.waa.project.sellingsystem.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("select o from Order o where o.userId=?1")
	List<Order> findByUserId(Long buyerId);

}
