package com.ep4test2.demo.controller;

import com.ep4test2.demo.entity.User;
import com.ep4test2.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;

    // Con esto accedemos a la pagina principal de login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Al darle al boton de login valida que el usuario exista en la db hecha a base de la entidad User,
    // si es valido nos manda a visitas
    // si no es valido nos devuelve a login pero mandando una variable error para que muestre un mensaje ya puesto en el html
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.authenticate(username, password)) {
            return "redirect:/visitas";
        } else {
            return "redirect:/login?error";
        }
    }

    // la opcion register deja al usuario crear un user a base de la entidad user, en otro html
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Al darle Save crea un registro con el modelo de User en la db de users
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        // aprovechamos el metodo de validacion para verificar no exista el usuario ya
        if (userService.isUserExists(user.getUsername())) {
            model.addAttribute("errorMessage", "Username already exists");
            return "register";
        }
        userService.createUser(user);
        return "redirect:/login";
    }
}