package cs545.waa.project.sellingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs545.waa.project.sellingsystem.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

}
