package service;

import java.util.List;

import model.Employee;

public interface EmployeeService {

	//create
	void add(Employee employee);
	
	//read
	String AllEmployee();
	List<Employee> findAllEmployee();
	Employee findByNumber(String number);

	Employee Login(String username, String password);
	
	//update
	
	//delete
}
