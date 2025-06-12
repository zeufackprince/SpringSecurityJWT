package com.helloIftekhar.springJwt.controller;

import com.helloIftekhar.springJwt.model.Role;
import com.helloIftekhar.springJwt.model.User;
import com.helloIftekhar.springJwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAll(){
        return (ResponseEntity<List<User>>) this.userRepository.findAll();
    }

    @GetMapping("getemail")
    public Optional<User> getemail(@PathVariable String email){
        return  this.userRepository.findByEmail(email);
    }

    @GetMapping("getrole")
    public List<User> getemail(@PathVariable Role role){
        return (List<User>) this.userRepository.findByRole(role);
    }

}
