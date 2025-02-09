package com.Tz.UserService;

import com.Tz.Exceptions.UserException;
import com.Tz.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws Exception;
    List<User> getAllUsers();;
    void deleteUser(Long id) throws UserException;
    User updateUser(Long id, User user) throws Exception;
}
