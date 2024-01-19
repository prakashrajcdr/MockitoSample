package com.prakash.customerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(OrderAnnotation.class)
public class AddCustomerServiceUnitTest {

	@Mock
	CustomerRepository customerRepo;
	
	@InjectMocks
	CustomerService customerService;	
	
	@Test
	public void addCustomerPositiveCase() {
		System.out.println("test 1");
		CustomerMaster customer = new CustomerMaster("Prakash", "Chennai");

		when(customerRepo.save(customer)).thenReturn(new CustomerMaster("Prakash", "Chennai"));
		
		CustomerMaster retCustomer = customerService.addCustomer(customer);
		
		verify(customerRepo).save(customer);
		assertEquals("Prakash", retCustomer.getName());

	}
	
	@Test
	@Order(2)
	public void addCustomerNegativeCase() {
		System.out.println("test 2");
		CustomerMaster customer = new CustomerMaster("rajesh", "delhi");

		when(customerRepo.save(customer)).thenReturn(new CustomerMaster("rajesh", "delhi"));

		CustomerMaster retCustomer = customerService.addCustomer(customer);
		
		verify(customerRepo).save(customer);
		assertNotEquals("Prakash", retCustomer.getName());

	}

	@Test
	@Order(1)
	public void addCustomerExceptionCase() {
		System.out.println("test 3");

		CustomerMaster emptyNameCustomer = new CustomerMaster("", "delhi");
		
		CustomException emptyNameException = assertThrows(CustomException.class, () -> customerService.addCustomer(emptyNameCustomer));
		assertTrue(emptyNameException.getMessage().contains("Customer Name can not"));
		assertTrue(emptyNameException.getHttpStatuCode().equals(HttpStatus.BAD_REQUEST));
		
		CustomerMaster twoCharCustomer = new CustomerMaster("ra", "delhi");
		CustomException twoCharNameException = assertThrows(CustomException.class, () -> customerService.addCustomer(twoCharCustomer));
		assertTrue(twoCharNameException.getMessage().contains("greater than 2 character"));
		assertTrue(twoCharNameException.getHttpStatuCode().equals(HttpStatus.BAD_REQUEST));
		
		CustomerMaster customer = new CustomerMaster("rajesh", "delhi");
		when(customerRepo.save(customer)).thenThrow(new RuntimeException("Manual exception"));
		Exception exception = assertThrows(Exception.class, () -> customerService.addCustomer(customer));
		verify(customerRepo).save(customer);

		assertThrows(Exception.class, () -> customerService.addCustomer(customer));
	}
	
}
