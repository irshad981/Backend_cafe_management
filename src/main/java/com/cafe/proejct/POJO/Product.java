package com.cafe.proejct.POJO;

import java.io.Serializable;



import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQuery(name = "Product.getAllProduct" , query = "select new com.cafe.proejct.wrapper.ProductWrapper(p.id , p.name , p.description , p.price ,p.status , p.category.id , p.category.name ) from Product p")

@NamedQuery(name = "Product.updateProductStatus" , query = "update Product p set p.status=:status where p.id=:id")

@NamedQuery(name = "Product.getProductByCategory" , query = "select new com.cafe.proejct.wrapper.ProductWrapper(p.id , p.name ) from Product p where p.category.id=:id and p.status='true'")

@NamedQuery(name = "Product.getProductById" , query = "select new com.cafe.proejct.wrapper.ProductWrapper(p.id , p.name , p.description , p.price) from Product p where p.id=:id")



@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "product")
public class Product implements Serializable{
	
	public static final Long serialVersionUid = 123456L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_fk" , nullable = false)
	private Category category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "status")
	private  String status;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Long getSerialversionuid() {
		return serialVersionUid;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", price=" + price + ", status=" + status + "]";
	}
	
	
	
	

}
