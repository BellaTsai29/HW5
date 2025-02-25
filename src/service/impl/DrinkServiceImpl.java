package service.impl;

import java.util.List;

import dao.impl.DrinkDaoImpl;
import model.Drink;
import service.DrinkService;

public class DrinkServiceImpl implements DrinkService {

	public static void main(String[] args) {
		System.out.println(new DrinkServiceImpl().findById(5));

	}

	private static DrinkDaoImpl drinkdaoimpl=new DrinkDaoImpl();
	
	@Override
	public void add(Drink drink) {
		if(drink.getGreen()>=0 && drink.getMilk()>=0 && drink.getBlack()>=0)
		{
			drinkdaoimpl.add(drink);
		}
		
	}


	@Override
	public String AllDrink() {
		List<Drink> l=drinkdaoimpl.selectAll();
		String show="";
		for(Drink d:l)
		{
			int sum=d.getMilk()*80+d.getGreen()*30+d.getBlack()*30;
			
			show=show+"ID:"+d.getid()+
					"\t奶茶:"+d.getMilk()+
					"\t綠茶:"+d.getGreen()+
					"\t紅茶"+d.getBlack()+
					"\t金額:"+sum+"元\n";
		}
		return show;
	}

	@Override
	public List<Drink> findAllDrink() {
		return  drinkdaoimpl.selectAll();
	}

	@Override
	public Drink findById(int id) {
		Drink drink=null;
		if(id>=0)
		{
			List<Drink> l=drinkdaoimpl.selectBy(id);
			if(l.size()>=0)
			{
				drink=l.get(0);
			}
			
		}	
		
		return drink;
	}

}
