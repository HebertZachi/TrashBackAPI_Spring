package br.com.fiap.traskBackAPI.service;

import br.com.fiap.traskBackAPI.model.User;
import br.com.fiap.traskBackAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registrar(User user){
        String senhaCripto = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(senhaCripto);

        return userRepository.save(user);
    }
}
