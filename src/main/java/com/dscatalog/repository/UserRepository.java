package com.dscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dscatalog.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
