package dao;

import java.util.List;

import model.Drink;

public interface DrinkDao{
	
	//create
	void add(Drink drink);
	
	//read
	 List<Drink> selectAll();
	 List<Drink>selectBy(int id);
	
	//update
	 void update(Drink drink);
	 
	
	//delete
	 void delete(int id);
	
	

}
