package com.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Auth;



public interface UserRepository extends JpaRepository<Auth,Long>{

    Optional<Auth>findByUsername(String username);
    boolean existsByUsername(String username);
    
}
