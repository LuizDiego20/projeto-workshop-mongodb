package com.luiz.workshop_mongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.workshop_mongodb.domain.User;
import com.luiz.workshop_mongodb.dto.UserDTO;
import com.luiz.workshop_mongodb.services.UserService;

@RestController 
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping 
    public ResponseEntity<List<UserDTO>> findAll(){
       
        List<User> list = userService.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
       
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

}
