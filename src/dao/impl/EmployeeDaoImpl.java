package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDao;
import model.Employee;
import util.DbConnection;

public class EmployeeDaoImpl implements EmployeeDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static Connection conn=DbConnection.getDb();
	
	@Override
	public void add(Employee employee) {
		String sql="insert into employee(number,name,phone,address,password) value(?,?,?,?,?)";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			
			preparedstatement.setString(1,employee.getNumber());
			preparedstatement.setString(2,employee.getName());
			preparedstatement.setInt(3,employee.getPhone());
			preparedstatement.setString(4,employee.getAddress());
			preparedstatement.setString(5,employee.getPassword());
			
			preparedstatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> selectAll() {
		String sql="select *from employee";
		List<Employee> l= new ArrayList();
		
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			ResultSet resultset=preparedstatement.executeQuery();
			while (resultset.next())
			{
				Employee employee=new Employee();
				employee.setNumber(resultset.getString("number"));
				employee.setName(resultset.getString("name"));
				employee.setPhone(resultset.getInt("phone"));
				employee.setAddress(resultset.getString("address"));
				employee.setAddress(resultset.getString("password"));
				l.add(employee);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}


	

	@Override
	public List<Employee> selectBy(String number) {
		String sql="select *from employee where number=?";
		List<Employee> l= new ArrayList();
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1,number);
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next()) {
				Employee employee=new Employee();
				employee.setNumber(resultset.getString("number"));
				employee.setName(resultset.getString("name"));
				employee.setPhone(resultset.getInt("phone"));
				employee.setAddress(resultset.getString("address"));
				employee.setAddress(resultset.getString("password"));
				l.add(employee);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		return l;
	}
	

	@Override
	public void update(Employee employee) {
		String sql="update employee set name=?,phone=?,address=? where number=?";
		try {
			PreparedStatement preparedstatement =conn.prepareStatement(sql);
			
			preparedstatement.setString(1, employee.getName());
			preparedstatement.setString(2, employee.getNumber());
			preparedstatement.setInt(3, employee.getPhone());
			preparedstatement.setString(4, employee.getAddress());
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public void delete(String number) {
		String sql="delete from employee where number=?";
		try {
			PreparedStatement preparedstatement =conn.prepareStatement(sql);
			
			preparedstatement.setString(1, number);
			preparedstatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<Employee> selectUsernameAndPassword(String username, String password) {
		String sql="select * from employee where number=? and password=?";
		List<Employee> l=new ArrayList();
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			
			ResultSet resultset=preparedstatement.executeQuery();
			
			if(resultset.next())
			{
				Employee E=new Employee();
				E.setNumber(resultset.getString("number"));
				E.setName(resultset.getString("name"));
				E.setPassword(resultset.getString("password"));
				E.setPhone(resultset.getInt("phone"));
				E.setAddress(resultset.getString("address"));
				E.setPassword(resultset.getString("password"));
				
				l.add(E);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public int FindMaxID() {
	    String sql = "SELECT MAX(id) AS max_id FROM employee";
	    try {
	        PreparedStatement preparedstatement = conn.prepareStatement(sql);
	        ResultSet rs = preparedstatement.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("max_id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	

}
