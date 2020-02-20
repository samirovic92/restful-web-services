package com.samirovic.restfulwebservices.controller;

import com.samirovic.restfulwebservices.dao.PostDao;
import com.samirovic.restfulwebservices.dao.UserDao;
import com.samirovic.restfulwebservices.exception.UserNotFoundException;
import com.samirovic.restfulwebservices.model.Post;
import com.samirovic.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @GetMapping("/users")
    public List<User> retrieveAllUser() {
        return userDao.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> creatUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {

        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        }
        // hateoas
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTO = linkTo(methodOn(this.getClass()).retrieveAllUser());
        resource.add(linkTO.withRel("all-users"));

        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        if (!userDao.findById(id).isPresent()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        }
        userDao.deleteById(id);
    }
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostByUser(@PathVariable int id){
        Optional<User> optionalUser = userDao.findById(id);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        }

        return optionalUser.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> creatUser(@PathVariable int id, @RequestBody Post post) {
        Optional<User> optionalUser = userDao.findById(id);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(String.format("User with id %d not found", id));
        }

        post.setUser(optionalUser.get());
        postDao.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
