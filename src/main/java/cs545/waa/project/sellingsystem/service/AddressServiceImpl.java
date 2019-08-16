package cs545.waa.project.sellingsystem.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs545.waa.project.sellingsystem.domain.Address;
import cs545.waa.project.sellingsystem.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Address findById(Integer addressId) {

		return addressRepository.findById(addressId).get();
	}

	@Override
	public void updateAddress(@Valid Address address) {
		
		addressRepository.save(address);
	}

}
