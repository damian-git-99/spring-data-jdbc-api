package com.github.damian_git_99.user.controller;

import com.github.damian_git_99.user.exceptions.UserNotFoundException;
import com.github.damian_git_99.user.model.User;
import com.github.damian_git_99.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @GetMapping("")
    List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    User findUser(@PathVariable(name = "id") Long id){
        return userService.findUserById(id)
                .orElseThrow( () -> new UserNotFoundException("User Not Found"));
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable(name = "id") Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("")
    void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

}
