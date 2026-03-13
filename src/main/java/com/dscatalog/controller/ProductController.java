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

import com.dscatalog.dto.ProductDTO;
import com.dscatalog.model.Product;
import com.dscatalog.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService ProductService;
	
	@GetMapping(value = "")
	public ResponseEntity<Page<ProductDTO>> findAll(
			@RequestParam(value = "page",defaultValue = "0" ) Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "12" ) Integer linesPerPage,
			@RequestParam(value = "direction",defaultValue = "ASC" ) String direction,
			@RequestParam(value = "orderBy",defaultValue = "name" ) String orderBy
			){
		
		PageRequest pageRequest= PageRequest.of(page,linesPerPage,Direction.valueOf(direction) ,orderBy);
		
		Page<ProductDTO> list = ProductService.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id){
		ProductDTO dto = ProductService.findById(id);
		return ResponseEntity.ok().body(dto);
		
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
		dto= ProductService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
		
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> insert(@PathVariable("id") Long id  ,@RequestBody ProductDTO dto){
		dto= ProductService.update(id,dto);

		return ResponseEntity.ok().body(dto);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable("id") Long id  ){
		ProductDTO dto = ProductService.delete(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	
	
}
