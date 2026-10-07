package com.luiz.workshop_mongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.workshop_mongodb.domain.Post;
import com.luiz.workshop_mongodb.repositories.PostRepository;
import com.luiz.workshop_mongodb.services.exception.ObjectNotFoundException;

@Service 
public class PostService {

    @Autowired 
    PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
