package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import model.Customer;
import model.Drink;
import model.Employee;
import util.DbConnection;

public class CustomerDaoImpl implements CustomerDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private static Connection conn=DbConnection.getDb();
	
	@Override
	public void add(Customer customer) {
		String sql="insert into cusromer(name,user,password,phone,address) values(?,?,?,?,?)";
		try {
			
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			
			preparedstatement.setString(1, customer.getName());
			preparedstatement.setString(2, customer.getUser());
			preparedstatement.setString(3, customer.getPassword());
			preparedstatement.setInt(4, customer.getPhone());
			preparedstatement.setString(5, customer.getAddress());
			
		}catch(SQLException e)
		{
		e.printStackTrace();
		}
	}

	@Override
	public List<Customer> selectAll() {
		String sql="select *from customer";
		List<Customer> l=new ArrayList<Customer>();
		
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			ResultSet resultset=preparedstatement.executeQuery();
		while(resultset.next()){
			
			Customer customer =new Customer();
		//	customer.setId(resultset.getInt("id"));
			customer.setName(resultset.getString("name"));
			customer.setUser(resultset.getString("user"));
			customer.setPassword(resultset.getString("password"));
			customer.setPhone(resultset.getInt("phone"));
			customer.setAddress(resultset.getString("address"));
			l.add(customer);	
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public List<Customer> selectBy(String name) {
		String sql="SELECT *from customer where name=?";
		List<Customer> l=new ArrayList<Customer>();
		
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, name);
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next())
			{
				Customer customer=new Customer();
				//customer.setId(resultset.getInt("id"));
				customer.setName(resultset.getString("name"));
				customer.setUser(resultset.getString("user"));
				customer.setPassword(resultset.getString("password"));
				customer.setPhone(resultset.getInt("phone"));
				customer.setAddress(resultset.getString("address"));
				l.add(customer);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public void update(Customer customer) {
		String sql="update customer set id=?,user=?,phone=?,address=? where name=?";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			//preparedstatement.setInt(1, customer.getId());
			preparedstatement.setString(2, customer.getUser());
			preparedstatement.setInt(3, customer.getPhone());
			preparedstatement.setString(4, customer.getAddress());
			preparedstatement.setString(5, customer.getName());
			
			preparedstatement.executeUpdate();
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
	}

	@Override
	public void delete(int id) {
		String sql="delete from customer where id=?";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Customer> selectUserAndPassword(String user, String password) {
		String sql="select * from customer where user=? and password=?";
		List<Customer> l=new ArrayList();
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, user);
			preparedstatement.setString(2, password);
			
			ResultSet resultset=preparedstatement.executeQuery();
			
			if(resultset.next())
			{
				Customer C=new Customer();
				C.setUser(resultset.getString("user"));
				C.setName(resultset.getString("name"));
				C.setPassword(resultset.getString("password"));
				C.setPhone(resultset.getInt("phone"));
				C.setAddress(resultset.getString("address"));
				
				l.add(C);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

}
