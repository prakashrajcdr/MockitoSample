package com.praksah.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praksah.entity.CustomerMaster;
import com.praksah.service.CustomerService;

@RestController
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/user")
	public Principal user(Principal principal) {
	    System.out.println("username : " + principal.getName());
	    return principal;
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerMaster> addCustomer(@RequestBody CustomerMaster customer){
		CustomerMaster retCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<CustomerMaster>(retCustomer, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<CustomerMaster> getCustomer(@PathVariable("id") Integer customerId) {
		CustomerMaster customer = customerService.getCustomer(customerId);
		return new ResponseEntity<CustomerMaster>(customer, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAllCustomers")
	public ResponseEntity<List<CustomerMaster>> getAllCustomers(){
		List<CustomerMaster> customers = customerService.getAllCustomers();
		return new ResponseEntity<List<CustomerMaster>>(customers, HttpStatus.OK);
	}

	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<CustomerMaster> updateCustomer(@PathVariable("id") Integer customerId, @RequestBody CustomerMaster customer){
		CustomerMaster retCustomer = customerService.updateCustomer(customerId, customer);
		return new ResponseEntity<CustomerMaster>(retCustomer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeCustomer/{id}")
	public ResponseEntity<CustomerMaster> removeCustomer(@PathVariable("id") Integer customerId){
		CustomerMaster customer =  customerService.removeCustomer(customerId);
		return new ResponseEntity<CustomerMaster>(customer, HttpStatus.CREATED);
	}
	
}
