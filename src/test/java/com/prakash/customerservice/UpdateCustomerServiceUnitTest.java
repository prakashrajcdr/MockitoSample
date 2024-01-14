package com.prakash.customerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.praksah.entity.CustomerMaster;
import com.praksah.exception.CustomException;
import com.praksah.repository.CustomerRepository;
import com.praksah.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerServiceUnitTest {

	@Mock
	CustomerRepository customerRepo;
	
	@InjectMocks
	CustomerService customerService;
	
	@Test
	public void positiveCase() {
		CustomerMaster customer = new CustomerMaster("Prakash", "Chennai");
		CustomerMaster updateCustomer = new CustomerMaster("Prakash Raj", "Erode");
		when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));

		when(customerRepo.save(customer)).thenReturn(new CustomerMaster("Prakash Raj", "Erode"));
		
		CustomerMaster retCustomer = customerService.updateCustomer(1, updateCustomer);
		
		verify(customerRepo).findById(anyInt());
		verify(customerRepo).save(customer);
		assertEquals("Prakash Raj", retCustomer.getName());

	}

	@Test
	public void exceptionCase() {
		CustomerMaster customer = new CustomerMaster("Prakash", "Chennai");
		
		CustomerMaster emptyNameCustomer = new CustomerMaster("", "Erode");
		CustomException emptyNameException = assertThrows(CustomException.class, () -> customerService.updateCustomer(1, emptyNameCustomer));
		assertTrue(emptyNameException.getMessage().contains("Customer Name can not"));
		assertTrue(emptyNameException.getHttpStatuCode().equals(HttpStatus.BAD_REQUEST));
		
		CustomerMaster twoCharCustomer = new CustomerMaster("ra", "delhi");
		CustomException twoCharNameException = assertThrows(CustomException.class, () -> customerService.updateCustomer(1, twoCharCustomer));
		assertTrue(twoCharNameException.getMessage().contains("greater than 2 character"));
		assertTrue(twoCharNameException.getHttpStatuCode().equals(HttpStatus.BAD_REQUEST));
		
		CustomerMaster properCustomer = new CustomerMaster("Prakash Raj", "Erode");
		when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
		when(customerRepo.save(properCustomer)).thenThrow(new RuntimeException("Manual exception"));
		Exception exception = assertThrows(Exception.class, () -> customerService.updateCustomer(1, customer));
		verify(customerRepo).findById(1);
		verify(customerRepo).save(customer);
		
		assertThrows(Exception.class, () -> customerService.updateCustomer(1, customer));
		
		when(customerRepo.findById(2)).thenReturn(Optional.empty());
		CustomException customException = assertThrows(CustomException.class, () -> customerService.updateCustomer(2, customer));
		verify(customerRepo).findById(2);
		assertTrue(customException.getMessage().contains("Customer Id not Exist"));
		
	}
	
	@Test
	public void negativeCase() {
		CustomerMaster customer = new CustomerMaster("Prakash", "Chennai");
		CustomerMaster updateCustomer = new CustomerMaster("Prakash Raj", "Erode");
		when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));

		when(customerRepo.save(customer)).thenReturn(new CustomerMaster("Prakash Raj", "Erode"));
		
		CustomerMaster retCustomer = customerService.updateCustomer(1, updateCustomer);
		
		verify(customerRepo).findById(anyInt());
		verify(customerRepo).save(customer);
		assertNotEquals("Prakash", retCustomer.getName());

	}
	

}
