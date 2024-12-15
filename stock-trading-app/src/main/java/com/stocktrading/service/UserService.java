package com.stocktrading.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stocktrading.model.User;
import com.stocktrading.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
    	
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public void updateUser(User user) {
        userRepository.save(user);
    }
    
    public boolean validateLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password); 
    }
    
}
