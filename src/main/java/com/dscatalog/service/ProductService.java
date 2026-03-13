package com.dscatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dscatalog.dto.CategoryDTO;
import com.dscatalog.dto.ProductDTO;
import com.dscatalog.exception.ControllerNotFoundException;
import com.dscatalog.model.Category;
import com.dscatalog.model.Product;
import com.dscatalog.repository.CategoryRepository;
import com.dscatalog.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository ProductRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		Page<Product>list = ProductRepository.findAll(pageRequest);
		
		return list.map(x -> new ProductDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById (Long id) {
		Optional<Product> obj = ProductRepository.findById(id);
		Product entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entity not found"));
		
		return new ProductDTO(entity,entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto,entity);
		entity = ProductRepository.save(entity);
		
		return new ProductDTO(entity);
	}

	

	@Transactional
	public ProductDTO update(Long id,ProductDTO dto) {
		try {
			
			Product entity = ProductRepository.getReferenceById(id);
			
			copyDtoToEntity(dto,entity);
			entity= ProductRepository.save(entity);
			
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id not found "+id);
		}
	}

	
	@Transactional
	public ProductDTO delete(Long id) {
		try {
			Optional<Product> entity = ProductRepository.findById(id);
			ProductRepository.delete(entity.get());
			
			return new ProductDTO(entity.get());
			
		} catch (Exception e) {
			throw new ControllerNotFoundException("Id not found "+id);
		}
		
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImg_url());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear();
		for(CategoryDTO catDto:dto.getCategories()) {
			Category category = categoryRepository.getReferenceById(catDto.getId());
			entity.getCategories().add(category);
		}
		
	}
	
}
