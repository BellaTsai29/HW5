package service;

import java.util.List;

import model.Drink;

public interface DrinkService {

	//create
	void add(Drink drink);
	
	//read
	String AllDrink();
	List<Drink> findAllDrink();
	Drink findById(int id);
	
	//update
	
	//delete
}
