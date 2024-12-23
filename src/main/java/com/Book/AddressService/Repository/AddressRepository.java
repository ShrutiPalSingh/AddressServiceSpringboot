package com.Book.AddressService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.AddressService.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
