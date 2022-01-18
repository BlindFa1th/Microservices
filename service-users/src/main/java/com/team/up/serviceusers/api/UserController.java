package com.team.up.serviceusers.api;

import com.team.up.serviceusers.repo.model.Userclass.User;
import com.team.up.serviceusers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public final class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getUsersById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/users/roles/{user_id}")
    public ResponseEntity getUserRoles(@PathVariable Long user_id){
        try{
            return ResponseEntity.ok(userService.getUserRoles(user_id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User newUser){
        return ResponseEntity.ok(userService.saveUser(newUser));
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        try {
            return ResponseEntity.ok(userService.updateUsersById(id, updatedUser));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUsersById(id));
    }
}

