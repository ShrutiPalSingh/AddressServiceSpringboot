package com.Book.AddressService.Service;

import java.util.List;
import java.util.Optional;

import com.Book.AddressService.Model.Address;

public interface AddressService {

	Address addAddress(Address address);
	Address updateAddress(Long addressId, Address address);
	List<Address> getAllAddress();
	Optional<Address> getAddressById(Long addressId);
	Address softAddressDelete(Long addressId);
	void deleteAddress(Long addressId);
}
