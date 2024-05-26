package br.com.fiap.traskBackAPI.controller;

import br.com.fiap.traskBackAPI.model.User;
import br.com.fiap.traskBackAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody User user){
        User userCadastrado = userService.registrar(user);
        return ResponseEntity.ok(userCadastrado);
    }
}


