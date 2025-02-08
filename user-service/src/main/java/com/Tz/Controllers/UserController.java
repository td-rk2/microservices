package com.Tz.Controllers;

import com.Tz.Exceptions.UserException;
import com.Tz.Models.User;
import com.Tz.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/getuser")
    public User getUser(User user){
        user.setFullName("Zayn man");
        user.setEmail("Zayn.man@gmail.com");
        user.setPhone(43664);
        user.setRole("Actor");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/getallusers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getuserbyid/{id}")
    public User getAllUsers(@PathVariable Long id) throws Exception{
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        throw new Exception("user not found");
    }

    @PostMapping("/createuser")
    public User createUser(@RequestBody @Valid User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable Long id) throws Exception{
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty())
        {
            throw new UserException("User not found with Id : "+id);
        }
        //userRepository.deleteById(id);
        userRepository.deleteById(opt.get().getId());
        return "User deleted";
    }

    @PutMapping("/updateuser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent())
        {
            User user = opt.get();
            user.setFullName(userDetails.getFullName());;
            user.setEmail(userDetails.getEmail());
            user.setRole("From hard code");
            return userRepository.save(user);
        }
        throw new Exception("User not found");
    }

    @PutMapping("/updateuserrequestless/{id}")
    public User updateUserrequestless(@PathVariable Long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent())
        {
            User user = opt.get();
            user.setFullName("less oil");;
            user.setEmail("less mail");
            user.setRole("From hard code1");
            return userRepository.save(user);
        }
        throw new Exception("User not found");
    }

}
