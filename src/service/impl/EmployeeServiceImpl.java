package service.impl;

import java.util.List;

import dao.impl.EmployeeDaoImpl;
import model.Drink;
import model.Employee;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static EmployeeDaoImpl employeedaoimpl=new EmployeeDaoImpl();

	@Override
	public void add(Employee employee) {
		employeedaoimpl.add(employee);
		
	}

	@Override
	public String AllEmployee() {
		List<Employee> l=employeedaoimpl.selectAll();
		String show="";
		for(Employee e:l)
		{
			
			show=show+"員工編號:"+e.getNumber()+
					"\t員工姓名:"+e.getName()+
					"\t員工電話:"+e.getPhone()+
					"\t員工地址:"+e.getAddress();
					
		}
		return show;
	}

	@Override
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		return employeedaoimpl.selectAll();
	}

	@Override
	public Employee findByNumber(String number) {
		Employee employee=null;
		  if (number == null || number.isEmpty()) {
			  
			  List<Employee> l=employeedaoimpl.selectBy(number);
				if(l.size()>=0)
				{
					employee=l.get(0);
				}
		        return null; 
		    }

		    return employee; 
	
	}
	
	@Override
	public Employee Login(String username, String password) {
		/*
		 * 1.判斷member
		 * true-->Member物件
		 * false-->null
		 */
		
		Employee employee=null;
		List<Employee> l=employeedaoimpl.selectUsernameAndPassword(username, password);
		if(l.size()!=0)
		{
			employee=l.get(0);
		}
		return employee;
	}


	

}
