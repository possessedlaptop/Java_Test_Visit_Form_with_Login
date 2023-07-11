package com.ep4test2.demo.repository;

import com.ep4test2.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Consulta con la base de datos el registro guardado de la tabla users
    public User findByUsername(String username);
}
