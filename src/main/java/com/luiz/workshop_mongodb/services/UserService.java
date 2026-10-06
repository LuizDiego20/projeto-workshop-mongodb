package com.luiz.workshop_mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.workshop_mongodb.domain.User;
import com.luiz.workshop_mongodb.repositories.UserRepository;

@Service 
public class UserService {

    @Autowired 
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
