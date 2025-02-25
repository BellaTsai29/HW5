package service;

import java.util.List;

import model.Customer;
import model.Employee;

public interface CustomerService {
	
	//create 
	void add(Customer customer);
	
	//read
	String AllCustomer();
	List<Customer> findAllCustomer();
	Customer findByUser(String user);
	Customer findByName(String name);

	Customer Login(String user, String password);
	
	//update
	
	//delete

}
