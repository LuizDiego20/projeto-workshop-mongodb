package com.luiz.workshop_mongodb.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luiz.workshop_mongodb.domain.Post;
import com.luiz.workshop_mongodb.domain.User;
import com.luiz.workshop_mongodb.dto.AuthorDTO;
import com.luiz.workshop_mongodb.dto.CommentDTO;
import com.luiz.workshop_mongodb.repositories.PostRepository;
import com.luiz.workshop_mongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, LocalDate.parse("21/03/2018", dtf).atTime(LocalTime.now()), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
        Post post2 = new Post(null, LocalDate.parse("21/03/2018", dtf).atTime(LocalTime.now()), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));

        CommentDTO com1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("20/05/2002", dtf), new AuthorDTO(alex));
        CommentDTO com2 = new CommentDTO("Aproveite!", LocalDate.parse("20/05/2002", dtf), new AuthorDTO(bob));
        CommentDTO com3 = new CommentDTO("Tenha um ótimo dia!", LocalDate.parse("20/05/2002", dtf), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(com1, com2));
        post2.getComments().add(com3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }

}
