package com.cafe.proejct.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;


//@NamedQueries({
//    @NamedQuery(name = "User.findByEmailId", query = "SELECT u FROM User u WHERE u.email = :email")
//})
@NamedQuery(name = "User.findByEmailId" , query = "Select u from User u where u.email = :email")

@NamedQuery(name = "User.getAllUser" , query = "Select new com.cafe.proejct.wrapper.UserWrapper(u.id,u.name,u.contactNumber,u.email ,u.status  ) from User u where u.role = 'user'")

@NamedQuery(name = "User.updateStatus" , query = "update User u set u.status=:status where u.id=:id")

@NamedQuery(name = "User.getAllAdmin" , query = "Select u.email from User u where u.role = 'admin'")

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "contactNumber")
	private String contactNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "role")
	private String role;

	public User() {
		
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String name, String contactNumber, String email, String password, String status,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.email = email;
		this.password = password;
		this.status = status;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionid() {
		return serialVersionID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", password=" + password + ", status=" + status + ", role=" + role + "]";
	}
	
	
	

}
