package com.Book.AddressService.Controller.Beans;


public class AddressResponse {

	private Long addressId;
	private String line1;
	private String line2;
	private String line3;
	private int pincode;
	private String city;
	
	public AddressResponse(Long addressId, String line1, String line2, String line3,int pincode, String city) {
		super();
		this.addressId=addressId;
		this.line1=line1;
		this.line2=line2;
		this.line3=line3;
		this.pincode=pincode;
		this.city=city;
	}
	public AddressResponse()
	{
		
	}
	
	
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
