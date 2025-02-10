package com.Tz.Controllers;

import com.Tz.Exceptions.UserException;
import com.Tz.Models.User;
import com.Tz.Repositories.UserRepository;
import com.Tz.UserService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createuser")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User createduser =  userService.createUser(user);
        return new ResponseEntity<>(createduser, HttpStatus.CREATED);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> user = userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable Long id) throws Exception{
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        User updateuser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateuser, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception{
    userService.deleteUser(id);
    return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
    }

}
