package br.com.danllopes.usermanagment.controllers;

import br.com.danllopes.usermanagment.domain.entities.Users;
import br.com.danllopes.usermanagment.dtos.UserDTO;
import br.com.danllopes.usermanagment.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody @Valid UserDTO data) {
        return new ResponseEntity<>(this.service.createUser(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(this.service.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(this.service.getOptionalUser(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<Users> updateStudent(@PathVariable String userId, @RequestBody @Valid UserDTO data) {
        return new ResponseEntity<>(this.service.updateUser(data, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteStudent(@PathVariable String userId) {
        this.service.removeUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
