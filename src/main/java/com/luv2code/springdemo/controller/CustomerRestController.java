package com.luv2code.springdemo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	//Add Mapping For GET /customers
	@RequestMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	//Add Mapping For GET /customers/{customerId}
	@RequestMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId) {
		Customer searchedCustomer = customerService.getCustomer(customerId);
		if(Objects.isNull(searchedCustomer)) {
				throw new CustomerNotFoundException("CustomerId is not found: "+customerId);
		}
	return customerService.getCustomer(customerId);
	}
	
	//Add PUT Mapping For PUT /customers -  Update A Customer
	@PostMapping("/customers")
	public Customer insertCustomers(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}
		
	//Add PUT Mapping For PUT /customers -  Update A Customer
	@PutMapping("/customers")
	public Customer updateCustomers(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	
	//Add DELETE Mapping For DELETE /customers -  delete A Customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomers(@PathVariable int customerId) {
		Customer searchedCustomer = customerService.getCustomer(customerId);
		if(Objects.isNull(searchedCustomer)) {
				throw new CustomerNotFoundException("CustomerId is not found: "+customerId);
		}
		customerService.deleteCustomer(customerId);
		return "The deleted customer id is: "+customerId;
	}

}
