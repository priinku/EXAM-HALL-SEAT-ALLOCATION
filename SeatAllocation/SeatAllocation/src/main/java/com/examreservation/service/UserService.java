package com.examreservation.service;


import com.examreservation.entity.User;
import com.examreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}

