package dao;

import java.util.List;

import model.Employee;

public interface EmployeeDao {

	
	//create
	void add(Employee employee);
	
	//read
	List<Employee>selectAll();
	List<Employee>selectBy(String number);
	
	//update
	void update(Employee employee);
	
	//delete
	void delete(String number);
	
	//
	int FindMaxID();
}
