package dao;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

public interface CustomerDao {
	
	//create 
	void add(Customer customer);
	
	//read
	List<Customer> selectAll();
	List<Customer> selectBy( String name);

	//update
	void update(Customer customer);
	
	//delete
	void delete(int id);
	
	
}
