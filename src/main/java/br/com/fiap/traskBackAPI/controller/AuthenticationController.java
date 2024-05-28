package br.com.fiap.traskBackAPI.controller;

import br.com.fiap.traskBackAPI.dto.TokenDTO;
import br.com.fiap.traskBackAPI.dto.UserSignInDTO;
import br.com.fiap.traskBackAPI.model.User;
import br.com.fiap.traskBackAPI.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

//    @PostMapping
//    public ResponseEntity login(@RequestBody User user) {
//
//        System.out.println(user);
//        UsernamePasswordAuthenticationToken usernamePassword =
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//
//        Authentication auth = authenticationManager.authenticate(usernamePassword);
//
//        String token = tokenService.generateToken((User) auth.getPrincipal());
//
//        return ResponseEntity.ok(new TokenDTO(token));
//    }

    @PostMapping
    public ResponseEntity login(@Valid @RequestBody UserSignInDTO userSignInDTO) {
        try {
        System.out.println(userSignInDTO);
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(userSignInDTO.email(), userSignInDTO.password());

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
        } catch (BadCredentialsException ex) {
            return buildErrorResponse("Email or password is incorrect", HttpStatus.UNAUTHORIZED);
        }
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody UserSignInDTO userSignInDTO) {
//        try {
//            UsernamePasswordAuthenticationToken usernamePassword =
//                    new UsernamePasswordAuthenticationToken(userSignInDTO.email(), userSignInDTO.password());
//
//            Authentication auth = authenticationManager.authenticate(usernamePassword);
//
//            String token = tokenService.generateToken((User) auth.getPrincipal());
//
//            return ResponseEntity.ok(new TokenDTO(token));
//        } catch (BadCredentialsException ex) {
//            return buildErrorResponse("Email or password is incorrect", HttpStatus.UNAUTHORIZED);
//        }
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        Map<String, String> response = new HashMap<>();
        response.put("messageError", errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("messageError", message);
        return new ResponseEntity<>(response, status);
    }
}



