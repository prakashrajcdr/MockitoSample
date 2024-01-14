package com.praksah.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.praksah.entity.CustomerMaster;
import com.praksah.exception.CustomException;
import com.praksah.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	public CustomerMaster addCustomer(CustomerMaster newCustomer) {
		CustomerMaster persistedCustomer = null;
		try {
			if(StringUtils.isBlank(newCustomer.getName()))
				throw new CustomException("Customer Name can not be Empty.", HttpStatus.BAD_REQUEST);
			else if(!isCustomerNameGreater2Char(newCustomer.getName()))
				throw new CustomException("Customer Name must be greater than 2 character.", HttpStatus.BAD_REQUEST);
			
			persistedCustomer = customerRepo.save(newCustomer);
		}catch (CustomException e) {
			throw new CustomException(e.getMessage(), e.getHttpStatuCode());
		}catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return persistedCustomer;
	}
	
	public CustomerMaster updateCustomer(Integer customerId, CustomerMaster udpatedustomer) {
		CustomerMaster persistedCustomer = null;
		if(StringUtils.isBlank(udpatedustomer.getName()))
			throw new CustomException("Customer Name can not be Empty.", HttpStatus.BAD_REQUEST);
		else if(!isCustomerNameGreater2Char(udpatedustomer.getName()))
			throw new CustomException("Customer Name must be greater than 2 character.", HttpStatus.BAD_REQUEST);

		try {
			CustomerMaster customer = getCustomer(customerId);
			customer.setLocation(udpatedustomer.getLocation());
			customer.setName(udpatedustomer.getName());
			persistedCustomer = customerRepo.save(customer);
		}catch (CustomException e) {
			throw new CustomException(e.getMessage(), e.getHttpStatuCode());
		}catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return persistedCustomer;
	}
	
	public CustomerMaster getCustomer(Integer customerId) {
		CustomerMaster customer = null;
		try {
			customer = customerRepo.findById(customerId).get();
		}catch (Exception e) {
			throw new CustomException("Customer Id not Exist.", HttpStatus.BAD_REQUEST);
		}
		return customer;
	}
	
	public List<CustomerMaster> getAllCustomers() {
		try {
			return customerRepo.findAll();
		}catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public CustomerMaster removeCustomer(Integer customerId) {
		CustomerMaster customer = null;
		try {
			customer = getCustomer(customerId);
			customerRepo.delete(customer);
		}catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return customer;
	}
	
	public boolean isCustomerNameGreater2Char(String cusName) {
		return cusName.length() > 2;
	}

}
