package com.dscatalog.controller;

import java.net.URI;



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

import com.dscatalog.dto.UserDTO;
import com.dscatalog.dto.UserInsertDTO;
import com.dscatalog.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "")
	public ResponseEntity<Page<UserDTO>> findAll(
			@RequestParam(value = "page",defaultValue = "0" ) Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "12" ) Integer linesPerPage,
			@RequestParam(value = "direction",defaultValue = "ASC" ) String direction,
			@RequestParam(value = "orderBy",defaultValue = "name" ) String orderBy
			){
		
		PageRequest pageRequest= PageRequest.of(page,linesPerPage,Direction.valueOf(direction) ,orderBy);
		
		Page<UserDTO> list = userService.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
		UserDTO dto = userService.findById(id);
		return ResponseEntity.ok().body(dto);
		
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto){
		UserDTO user= userService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> insert(@PathVariable("id") Long id  ,@Valid @RequestBody UserDTO dto){
		dto= userService.update(id,dto);

		return ResponseEntity.ok().body(dto);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable("id") Long id  ){
		UserDTO dto = userService.delete(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	
	
}
