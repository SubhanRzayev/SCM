package com.scm.services.impl;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepositories;
import com.scm.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Data
@Service
public class UserServiceImpl implements UserService {

    private  final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

//    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public User saveUser(User user) {
//        String userId = UUID.randomUUID().toString();
        //password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        //user.setPassword(userId)
        return userRepositories.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepositories.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user1 = userRepositories.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user1.setName(user1.getName());
        user1.setEmail(user1.getEmail());
        user1.setPassword(user1.getPassword());
        user1.setPhoneNumber(user1.getPhoneNumber());
        user1.setAbout(user1.getAbout());
        user1.setProfilePicture(user.getProfilePicture());
        user1.setEnabled(user.isEnabled());
        user1.setPhoneNumberVerified(user.isPhoneNumberVerified());
        user1.setEmailVerified(user.isEmailVerified());
        user1.setProvider(user.getProvider());
        user1.setProviderUserId(user.getProviderUserId());
        userRepositories.save(user1);
        return Optional.of(user1);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepositories.delete(user);
    }

    @Override
    public boolean isUserExist(Long userId) {
        User user2 = userRepositories.findById(userId).orElse(null);
        return user2 != null ? true : false;

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepositories.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }
}
