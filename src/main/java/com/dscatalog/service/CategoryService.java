package com.dscatalog.service;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.dscatalog.dto.CategoryDTO;
import com.dscatalog.model.Category;
import com.dscatalog.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@Transactional(readOnly = true)
	@GetMapping(value = "")
	public List<CategoryDTO> findAll(){
		List<Category>list = categoryRepository.findAll();
		
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
	}
}
