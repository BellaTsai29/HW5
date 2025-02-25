package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import dao.DrinkDao;
import model.Drink;
import util.DbConnection;

public class DrinkDaoImpl implements DrinkDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static Connection conn =DbConnection.getDb();

	@Override
	public void add(Drink drink) {
		String Sql="insert into porder(milk,black,green,name) values(?,?,?,?)";
		try {
			PreparedStatement preparedstatement =conn.prepareStatement(Sql);
			
			preparedstatement.setInt(1, drink.getMilk());
			preparedstatement.setInt(2, drink.getBlack());
			preparedstatement.setInt(3, drink.getGreen());
			preparedstatement.setString(4, drink.getName());
			
			preparedstatement.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<Drink> selectAll() {
		String sql="select *from porder";
		List<Drink> l=new ArrayList();
		
		try {
			PreparedStatement preparedstatement =conn.prepareStatement(sql);
			ResultSet resultset=preparedstatement.executeQuery();
			while (resultset.next())
			{
				Drink drink =new Drink();
				drink.setId(resultset.getInt("id"));
				drink.setName(resultset.getString("name"));
				drink.setMilk(resultset.getInt("milk"));
				drink.setGreen(resultset.getInt("green"));
				drink.setBlack(resultset.getInt("black"));
				l.add(drink);	
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Drink> selectBy(int id) {
		String Sql="select * from porder where id=?";
		List<Drink> l=new ArrayList();
		
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(Sql);
			preparedstatement.setInt(1, id);
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next())
			{
				Drink drink=new Drink();
				drink.setId(resultset.getInt("id"));
				drink.setName(resultset.getString("name"));
				drink.setMilk(resultset.getInt("milk"));
				drink.setGreen(resultset.getInt("green"));
				drink.setBlack(resultset.getInt("black"));
				l.add(drink);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	
	}

	@Override
	public void update(Drink drink) {
		String sql="update porder set name=?,lcd=?,ram=?,mouse=? where id=?";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, drink.getName());
			preparedstatement.setInt(2, drink.getMilk());
			preparedstatement.setInt(3, drink.getBlack());
			preparedstatement.setInt(4, drink.getGreen());
			preparedstatement.setInt(5, drink.getid());
			
			preparedstatement.executeUpdate();
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql="delete from porder where id=?";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}}
