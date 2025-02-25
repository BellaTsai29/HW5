package model;

public class Employee {

	private String number;
	private String name;
	private Integer phone;
	private String address;
	private String password;
	
	public  Employee(String number,String name,Integer phone,String address,String password) {
		this.number =number;
		this.name=name;
		this.phone=phone;
		this.address=address;
		this.password=password;
		
		
	}
	
	public Employee() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	}

	


