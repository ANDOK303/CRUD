package com.alejandromax.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro/guardar")
    public String registrar(@RequestParam String username, @RequestParam String password) {
        // Creamos el nuevo usuario con la contraseña encriptada
        UserDetails nuevoUsuario = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        // Lo guardamos en el manager que vive en la RAM
        userDetailsManager.createUser(nuevoUsuario);

        // Redirigimos al login
        return "redirect:/login?success";
    }
}