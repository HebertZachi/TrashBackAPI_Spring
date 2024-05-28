package br.com.fiap.traskBackAPI.service;

import br.com.fiap.traskBackAPI.dto.UserSignUpDTO;
import br.com.fiap.traskBackAPI.dto.UserSignUpReturnDTO;
import br.com.fiap.traskBackAPI.model.User;
import br.com.fiap.traskBackAPI.model.UserRole;
import br.com.fiap.traskBackAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserSignUpReturnDTO register(UserSignUpDTO userSignUpDTO) {
        User user = new User();
        user.setName(userSignUpDTO.name());
        user.setEmail(userSignUpDTO.email());
        String hashedPassword = new BCryptPasswordEncoder().encode(userSignUpDTO.password());
        user.setPassword(hashedPassword);
        user.setRole(UserRole.valueOf(userSignUpDTO.role().toUpperCase()));

        User savedUser = userRepository.save(user);
        return new UserSignUpReturnDTO(savedUser);
    }
}
