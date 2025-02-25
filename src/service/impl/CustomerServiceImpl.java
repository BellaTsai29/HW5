package service.impl;

import java.util.List;

import dao.impl.CustomerDaoImpl;
import model.Customer;
import model.Employee;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static CustomerDaoImpl customerdaoimpl=new CustomerDaoImpl();

	@Override
	public void add(Customer customer) {
		customerdaoimpl.add(customer);
		
	}

		@Override
		public String AllCustomer() {
		List<Customer> l=customerdaoimpl.selectAll();
		String show="";
		for(Customer c:l)
		{

			show=show+"客戶帳號:"+c.getUser()+
					"\t客戶姓名:"+c.getName()+
					"\t客戶電話:"+c.getPhone()+
					"\t客戶地址:"+c.getAddress();
					
		}
			
		return show;
	}

	@Override
	public List<Customer> findAllCustomer() {
		
		return customerdaoimpl.selectAll();
	}
	

	@Override
	public Customer findByUser(String user) {
		Customer customer=null;
		  if (user == null || user.isEmpty()) {
			  
			  List<Customer> l=customerdaoimpl.selectBy(user);
				if(l.size()>=0)
				{
					customer=l.get(0);
				}
		        return null; 
		    }

		    return customer; 
	}

	@Override
	public Customer findByName(String name) {
		Customer customer=null;
		  if (name == null || name.isEmpty()) {
			  
			  List<Customer> l=customerdaoimpl.selectBy(name);
				if(l.size()>=0)
				{
					customer=l.get(0);
				}
		        return null; 
		    }

		    return customer; 
	}

	@Override
	public Customer Login(String user, String password) {
		Customer customer=null;
		List<Customer> l=customerdaoimpl.selectUserAndPassword(user, password);
		if(l.size()!=0)
		{
			customer=l.get(0);
		}
		return customer;

}}
