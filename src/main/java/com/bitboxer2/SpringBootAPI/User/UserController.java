package com.bitboxer2.SpringBootAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@CrossOrigin( origins = {"http://localhost:5173"})
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users/get")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/users/create")
    public String createUser(@RequestBody User user){
        userService.createUser(user);
        return "The User was created correctly";
    }

    @DeleteMapping("/users/delete/{id}")
    public String createUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "The User was deleted correctly";
    }


}
