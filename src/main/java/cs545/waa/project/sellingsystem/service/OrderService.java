package cs545.waa.project.sellingsystem.service;

import java.util.List;

import cs545.waa.project.sellingsystem.domain.Order;

public interface OrderService {
	
	List<Order> findOrdersByBuyerId(Long buyerId);

}
