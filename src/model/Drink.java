package model;

public class Drink {
	
	private Integer milk;
	private Integer black;
	private Integer green;
	private String name;
	private Integer sum;
	private Integer id;
		
		public Drink (Integer milk,Integer black,Integer green,String name) 
		{
		if ( milk >=0 && black>=0 && green>=0)
		this.milk=milk;
		this.black=black;
		this.green=green;
		this.name=name;
		this.id=id;
		sum=milk*80+black*30+green*30;
		
		
		}
		public Drink() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getMilk() {
			return milk;
		}

		public void setMilk(Integer milk) {
			this.milk = milk;
		}

		public Integer getBlack() {
			return black;
		}

		public void setBlack(Integer black) {
			this.black = black;
		}

		public Integer getGreen() {
			return green;
		}

		public void setGreen(Integer green) {
			this.green = green;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer setsum() {
			return sum;
		}

		public void setId(int int1) {
			// TODO Auto-generated method stub
			
		}

		public int getid() {
			// TODO Auto-generated method stub
			return id;
		}
	}


