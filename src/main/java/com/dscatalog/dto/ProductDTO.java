package com.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;

import com.dscatalog.model.Category;
import com.dscatalog.model.Product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	@Size(min = 5,max = 60,message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo obrigatorio")
	private String name;
	
	
	@Size(min = 5,message = "Deve ter pelo menos 5 caracteres")
	@NotBlank(message = "Campo obrigatorio")
	private String description;
	
	@Positive(message = "Preco deve ser positivo")
	private Double price;
	
	private String img_url;
	
	@PastOrPresent(message = "A data do produto nao pode ser futura")
	private Instant date;
	
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO() {
		
	}

	public ProductDTO(Long id, String name, String description, Double price, String img_url, Instant date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.img_url = img_url;
		this.date = date;
	}
	
	public ProductDTO(Product entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.img_url = entity.getImgUrl();
		this.date = entity.getDate();
	}
	
	public ProductDTO(Product entity,Set<Category> categories) {
		this(entity);
		
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
	
	
	
	
}
