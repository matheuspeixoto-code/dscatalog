package com.dscatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dscatalog.dto.CategoryDTO;
import com.dscatalog.exception.ControllerNotFoundException;
import com.dscatalog.model.Category;
import com.dscatalog.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category>list = categoryRepository.findAll();
		
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById (Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entity not found"));
		
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = categoryRepository.save(entity);
		
		return new CategoryDTO(entity);
	}

	
	@Transactional
	public CategoryDTO update(Long id,CategoryDTO dto) {
		try {
			
			Category entity = categoryRepository.getReferenceById(id);
			
			entity.setName(dto.getName());
			entity= categoryRepository.save(entity);
			
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id not found "+id);
		}
	}

	
	@Transactional
	public CategoryDTO delete(Long id) {
		try {
			Optional<Category> entity = categoryRepository.findById(id);
			categoryRepository.delete(entity.get());
			
			return new CategoryDTO(entity.get());
			
		} catch (Exception e) {
			throw new ControllerNotFoundException("Id not found "+id);
		}
		
	}
	
	
}
