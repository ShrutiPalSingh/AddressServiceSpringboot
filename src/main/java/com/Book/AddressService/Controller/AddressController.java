package com.Book.AddressService.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Book.AddressService.Controller.Beans.AddressRequest;
import com.Book.AddressService.Controller.Beans.AddressResponse;
import com.Book.AddressService.Controller.Beans.GetAddressResponse;
import com.Book.AddressService.Exceptions.AddressNotFoundException;
import com.Book.AddressService.Model.Address;
import com.Book.AddressService.Service.AddressService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest, BindingResult bindingResult)
	{
		Address address = new Address(null,addressRequest.getLine1(),addressRequest.getLine2(),addressRequest.getLine3(),addressRequest.getPincode(),addressRequest.getCity());
		Address newAddress = addressService.addAddress(address);
		
		AddressResponse addressResponse = new AddressResponse(newAddress.getAddressId(), newAddress.getLine1(),newAddress.getLine2(),newAddress.getLine3(),newAddress.getPincode(),newAddress.getCity());
		
		return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<GetAddressResponse> updateAddress(@PathVariable(value = "addressId") Long addressId, @RequestBody AddressRequest addressRequest)throws AddressNotFoundException
	{
		Address address = new Address(null,addressRequest.getLine1(),addressRequest.getLine2(),addressRequest.getLine3(),addressRequest.getPincode(),addressRequest.getCity());
		Address newAddress = addressService.updateAddress(addressId, address);
		AddressResponse addressResponse = new AddressResponse(newAddress.getAddressId(),newAddress.getLine1(), newAddress.getLine2(), newAddress.getLine3(), newAddress.getPincode(), newAddress.getCity());
		List<AddressResponse> addressResponseList = Collections.singletonList(addressResponse);
		return new ResponseEntity<>(new GetAddressResponse(addressResponseList), HttpStatus.OK);
	}
	
	@GetMapping("/addresses")
	public ResponseEntity<GetAddressResponse> getAllAddresses()
	{
		List<Address> addresses = addressService.getAllAddress();
		List<AddressResponse> addressResponseList = addresses
				.stream()
				.map(address -> new AddressResponse(address.getAddressId(),address.getLine1(),address.getLine2(),address.getLine3(),address.getPincode(),address.getCity()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(new GetAddressResponse(addressResponseList), HttpStatus.OK);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<GetAddressResponse> getAddressById(@PathVariable(value = "addressId") Long addressId)throws AddressNotFoundException
	{
		Optional<Address> addressOptional = addressService.getAddressById(addressId);
		if(addressOptional.isEmpty())
		{
			throw new AddressNotFoundException("Address not found "+ addressId+ " not found");
		}
		Address address = addressOptional.get();
		List<AddressResponse> addressResponseList = Collections.singletonList(new AddressResponse(address.getAddressId(),address.getLine1(),address.getLine2(),address.getLine3(),address.getPincode(),address.getCity()));
		return new ResponseEntity<>(new GetAddressResponse(addressResponseList), HttpStatus.OK);
	}
	
	@PutMapping("/delete/{addressId}")
	public ResponseEntity<String> markAddressAsDeleted(@PathVariable(value = "addressId") Long addressId)
	{
		if (addressId == null)
		{
	        throw new IllegalArgumentException("ID must not be null");
	    }
		try {
			Address existingAddress = addressService.softAddressDelete(addressId);
			return new ResponseEntity<>("Address with id "+ addressId+ " marked deleted", HttpStatus.OK);
		}
		catch(AddressNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable(value = "addressId") Long addressId)
	{
		if (addressId == null)
		{
	        throw new IllegalArgumentException("ID must not be null");
	    }
		try {
			addressService.deleteAddress(addressId);
			return ResponseEntity.ok("Address deleted successfully!");
		}
		catch(AddressNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
}
