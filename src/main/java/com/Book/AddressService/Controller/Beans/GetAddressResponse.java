package com.Book.AddressService.Controller.Beans;

import java.util.List;

import lombok.Value;

@Value
public class GetAddressResponse {

	private List<AddressResponse> addresses;
	
	public GetAddressResponse(List<AddressResponse> addressResponseList)
	{
		this.addresses = addressResponseList;
	}
	
	public List<AddressResponse> getAddresses()
	{
		return addresses;
	}
	
	public void setAddresses(List<AddressResponse> addresses)
	{
		this.addresses = addresses;
	}
}
