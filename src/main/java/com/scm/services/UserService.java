package com.scm.services;

import com.scm.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> updateUser(User user);
    void deleteUser(Long userId);
    boolean isUserExist(Long userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();



}
