package com.prakash.customerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.praksah.entity.CustomerMaster;
import com.praksah.exception.CustomException;
import com.praksah.repository.CustomerRepository;
import com.praksah.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class GetCustomerServiceUnitTest {

	@Mock
	CustomerRepository customerRepo;
	
	@InjectMocks
	CustomerService customerService;	
	

	@Test
	public void positiveCase() {
		when(customerRepo.findById(anyInt())).thenReturn(Optional.of(new CustomerMaster("Prakash", "Chennai")));
		
		CustomerMaster retCustomer = customerService.getCustomer(1);
		assertEquals("Prakash", retCustomer.getName());
		verify(customerRepo).findById(anyInt());

	}
	
	@Test
	public void exceptionCase() {
		when(customerRepo.findById(anyInt())).thenReturn(Optional.empty());
		
		CustomException exception = assertThrows(CustomException.class, () -> customerService.getCustomer(1));
		verify(customerRepo).findById(anyInt());
		assertTrue(exception.getMessage().contains("Customer Id not Exist"));
	}

	@Test
	public void negativeCase() {
		when(customerRepo.findById(anyInt())).thenReturn(Optional.of(new CustomerMaster("Prakash", "Chennai")));
		
		CustomerMaster retCustomer = customerService.getCustomer(1);
		assertNotEquals("Ram", retCustomer.getName());
		verify(customerRepo).findById(anyInt());

	}
	
}
