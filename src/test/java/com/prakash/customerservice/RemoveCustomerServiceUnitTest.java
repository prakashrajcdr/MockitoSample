package com.prakash.customerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
class RemoveCustomerServiceUnitTest {

	@Mock
	CustomerRepository customerRepo;
	
	@InjectMocks
	CustomerService customerService;
	
	@Test
	public void positiveCase() {
		CustomerMaster customer = new CustomerMaster("Prakash", "Chennai");
		when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));
		doNothing().when(customerRepo).delete(customer);
		
		CustomerMaster retCustomer = customerService.removeCustomer(1);
		verify(customerRepo).findById(anyInt());
		verify(customerRepo).delete(customer);

	}

	@Test
	public void exceptionCase() {
		when(customerRepo.findById(anyInt())).thenReturn(Optional.empty());
		
		CustomException exception = assertThrows(CustomException.class, () -> customerService.removeCustomer(1));
		assertTrue(exception.getMessage().contains("Customer Id not Exist"));
		verify(customerRepo).findById(anyInt());

		CustomerMaster customer = new CustomerMaster("Prakash", "Chennai");
		when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));
		doThrow(CustomException.class).when(customerRepo).delete(customer);
		Exception deleteException = assertThrows(CustomException.class, () -> customerService.removeCustomer(1));
		verify(customerRepo).delete(customer);
	}
	
}
