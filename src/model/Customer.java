package model;

public class Customer {
	private Integer id;
	private String name;
	private String user;
	private String password;
	private Integer phone;
	private String address;
	
	public Customer(String name, String user,String password,Integer phone,String address) {
		//this.id=id;
		this.name=name;
		this.user=user;
		this.password=password;
		this.phone=phone;
		this.address=address;
	}

	public Customer() {
		super();
	}
	
	//public Integer getId() {
		//return id;
	//}

	/*
	public void setId(Integer id) {
		this.id = id;
	}
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
