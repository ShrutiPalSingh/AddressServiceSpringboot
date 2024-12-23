package com.Book.AddressService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.AddressService.Exceptions.AddressNotFoundException;
import com.Book.AddressService.Model.Address;
import com.Book.AddressService.Repository.AddressRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	//method to save new address
	@Override 
	public Address addAddress(Address address)
	{
		return addressRepository.save(address);
	}

	//method to update existing address
	@Override
	public Address updateAddress(Long addressId, Address address) throws AddressNotFoundException
	{
		//Find the existing address by ID
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		//if not found, throw custom exception
		if(addressOptional.isEmpty())
		{
			throw new AddressNotFoundException("Address with ID" + addressId + "not found");
		}
		//get the existing address
		Address existingAddress = addressOptional.get();
		//update the only fields that are provided in the request
		if(address.getLine1() != null) 
		{
			existingAddress.setLine1(address.getLine1());
		}
		if(address.getLine2() != null)
		{
			existingAddress.setLine2(address.getLine2());
		}
		if(address.getLine3() != null)
		{
			existingAddress.setLine3(address.getLine3());
		}
		if(address.getPincode() != 0)
		{
			existingAddress.setPincode(address.getPincode());
		}
		if(address.getCity() != null)
		{
			existingAddress.setCity(address.getCity());
		}
		//save the updated address to the repository
		return addressRepository.save(existingAddress);
	}

	//method to get all the addresses
	@Override
	public List<Address> getAllAddress() 
	{
		return addressRepository.findAll();
	}

	//method to get address by id
	@Override
	public Optional<Address> getAddressById(Long id)
	{
		return addressRepository.findById(id);
	}

	//method to mark address as deleted but not deleted from database
	@Override
	public Address softAddressDelete(Long addressId)throws AddressNotFoundException
	{
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		
		if(addressOptional.isEmpty())
		{
			throw new AddressNotFoundException("Address with id "+addressId+ " not found");
		}
		Address existingAddress = addressOptional.get();
		existingAddress.setIsdeleted(true);
		return addressRepository.save(existingAddress);
	}

	//delete address from database
	@Override
	public void deleteAddress(Long addressId) throws AddressNotFoundException
	{
		if(!addressRepository.existsById(addressId))
		{
			throw new AddressNotFoundException("Address with id "+ addressId+ " not found");
		}
		addressRepository.deleteById(addressId);
	}

}
