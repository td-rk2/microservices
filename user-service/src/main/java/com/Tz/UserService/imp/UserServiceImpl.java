package com.Tz.UserService.imp;

import com.Tz.Exceptions.UserException;
import com.Tz.Models.User;
import com.Tz.Repositories.UserRepository;
import com.Tz.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws Exception{
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        throw new Exception("user not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty())
        {
            throw new UserException("User not found with Id : "+id);
        }
        //userRepository.deleteById(id);
        userRepository.deleteById(opt.get().getId());

    }

    @Override
    public User updateUser(Long id, User user) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty())
        {
            throw new Exception("User not found with id: "+id);
        }
        User existinguser = opt.get();
        existinguser.setFullName(user.getFullName());;
        existinguser.setEmail(user.getEmail());
        existinguser.setRole(user.getRole());

        return userRepository.save(existinguser);
    }
}
