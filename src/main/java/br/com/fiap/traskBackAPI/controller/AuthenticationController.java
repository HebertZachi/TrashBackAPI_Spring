package br.com.fiap.traskBackAPI.controller;

import br.com.fiap.traskBackAPI.dto.TokenDTO;
import br.com.fiap.traskBackAPI.model.User;
import br.com.fiap.traskBackAPI.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody User user) {

        System.out.println(user);
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }
}



