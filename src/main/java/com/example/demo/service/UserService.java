package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerNewUserAccount(UserDTO userDTO) {

        User u = new User();
        u.setEnabled(1);
        u.setUsername(userDTO.getUserName());
        u.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        u.setRole("ROLE_USER");

        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setEmail(userDTO.getEmail());
        u.setPalier(0);

        return userRepository.save(u);
    }



    public User updateUser(UserDTO userDTO) {

        User u = new User();
        u.setId(userDTO.getId());
        u.setEnabled(1);
        u.setUsername(userDTO.getUserName());
        u.setRole(userDTO.getRole());
        u.setPassword(userDTO.getPassword());
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setEmail(userDTO.getEmail());
        u.setPalier(userDTO.getPalier());

        return userRepository.save(u);
    }


    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
