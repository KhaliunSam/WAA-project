package cs545.waa.project.sellingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs545.waa.project.sellingsystem.domain.Order;
import cs545.waa.project.sellingsystem.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> findOrdersByBuyerId(Long buyerId) {
		
		return orderRepository.findByUserId(buyerId);
	}

}
