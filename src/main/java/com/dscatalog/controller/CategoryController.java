package com.dscatalog.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dscatalog.dto.CategoryDTO;
import com.dscatalog.model.Category;
import com.dscatalog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "")
	public ResponseEntity<Page<CategoryDTO>> findAll(
			@RequestParam(value = "page",defaultValue = "0" ) Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "12" ) Integer linesPerPage,
			@RequestParam(value = "direction",defaultValue = "ASC" ) String direction,
			@RequestParam(value = "orderBy",defaultValue = "name" ) String orderBy
			){
		
		PageRequest pageRequest= PageRequest.of(page,linesPerPage,Direction.valueOf(direction) ,orderBy);
		
		Page<CategoryDTO> list = categoryService.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Long id){
		CategoryDTO dto = categoryService.findById(id);
		return ResponseEntity.ok().body(dto);
		
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@Valid @RequestBody CategoryDTO dto){
		dto= categoryService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> insert(@PathVariable("id") Long id  ,@Valid @RequestBody CategoryDTO dto){
		dto= categoryService.update(id,dto);

		return ResponseEntity.ok().body(dto);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Long id  ){
		CategoryDTO dto = categoryService.delete(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	
	
}
