package cs545.waa.project.sellingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs545.waa.project.sellingsystem.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
