package com.dscatalog.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.dscatalog.model.Role;
import com.dscatalog.dto.RoleDTO;
import com.dscatalog.dto.UserDTO;
import com.dscatalog.exception.ControllerNotFoundException;
import com.dscatalog.model.User;
import com.dscatalog.repository.RoleRepository;
import com.dscatalog.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(PageRequest pageRequest){
		Page<User>list = userRepository.findAll(pageRequest);
		
		return list.map(x -> new UserDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById (Long id) {
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entity not found"));
		
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = userRepository.save(entity);
		
		return new UserDTO(entity);
	}

	
	@Transactional
	public UserDTO update(Long id,UserDTO dto) {
		try {
			
			User entity = userRepository.getReferenceById(id);
			
			copyDtoToEntity(dto, entity);
			entity= userRepository.save(entity);
			
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id not found "+id);
		}
	}

	
	@Transactional
	public UserDTO delete(Long id) {
		try {
			Optional<User> entity = userRepository.findById(id);
			userRepository.delete(entity.get());
			
			return new UserDTO(entity.get());
			
		} catch (Exception e) {
			throw new ControllerNotFoundException("Id not found "+id);
		}
		
	}
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setFirstName(dto.getFirstName());
		entity.setEmail(dto.getEmail());
		entity.setLastName(dto.getLastName());
		entity.setPassword(dto.getPassword());
		
		entity.getRoles().clear();
		for(RoleDTO role: dto.getRoles()) {
			Role roleAdd = roleRepository.getReferenceById(role.getId());
			entity.getRoles().add(roleAdd);
		}
		
	}
	
}
