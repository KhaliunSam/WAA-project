package cs545.waa.project.sellingsystem.service;

import javax.validation.Valid;

import cs545.waa.project.sellingsystem.domain.Address;

public interface AddressService {

	Address findById(Integer addressId);

	void updateAddress(@Valid Address address);
	
	

}
