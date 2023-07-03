package br.com.diego.apifinancehub.controller;


import br.com.diego.apifinancehub.dto.CreateDepositDto;
import br.com.diego.apifinancehub.dto.CreateUserDto;
import br.com.diego.apifinancehub.model.User;
import br.com.diego.apifinancehub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody final CreateUserDto userData) {
        final User createUser = userService.createUser(userData);

        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> readUsers(){
        final List<User> readUsers = userService.readUsers();

        return new ResponseEntity<>(readUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> reatrieveUser(@PathVariable final String id) {
        final User user = userService.reatrieveUser(Long.parseLong(id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUSer(@Valid @RequestBody final CreateUserDto userData, @PathVariable final String id) {
        final User updateUser = userService.updateUser(userData, Long.parseLong(id));

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        userService.deleteUser(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> createDeposit(@Valid @RequestBody final CreateDepositDto depositData, @PathVariable final String id) {
        final User depositedUser = userService.createDeposit(depositData, Long.parseLong(id));

        return new ResponseEntity<>(depositedUser, HttpStatus.CREATED);
    }
}

