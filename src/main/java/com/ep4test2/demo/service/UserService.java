package com.ep4test2.demo.service;

import com.ep4test2.demo.entity.User;
import com.ep4test2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Importante, este metodo crea una variable user con el registro de la bd que coincide con el username mandado
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        // si el usename si existe, y la password mandadada es igual manda TRUE
        return user != null && user.getPassword().equals(password);
    }

    //Crea un registro a partir de la entidad y este se guarda en la bd
    public void createUser(User user) {
        userRepository.save(user);
    }

    // verifica si existe el usuario por username, no valida la contraseña pero se usara mas adelante para validar no copiamos un usuario
    public boolean isUserExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}

