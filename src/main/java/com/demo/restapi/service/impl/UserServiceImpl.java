package com.demo.restapi.service.impl;

import com.demo.restapi.exception.AccessDeniedException;
import com.demo.restapi.exception.AppException;
import com.demo.restapi.exception.BadRequestException;
import com.demo.restapi.exception.ResourceNotFoundException;
import com.demo.restapi.exception.UnauthorizedException;
import com.demo.restapi.model.User;
import com.demo.restapi.payload.ApiResponse;
import com.demo.restapi.payload.InfoRequest;
import com.demo.restapi.payload.UserIdentityAvailability;
import com.demo.restapi.payload.UserProfile;
import com.demo.restapi.payload.UserSummary;
import com.demo.restapi.repository.UserRepository;
import com.demo.restapi.security.UserPrincipal;
import com.demo.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(),
                currentUser.getLastName());
    }

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public UserIdentityAvailability checkEmailAvailability(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public UserProfile getUserProfile(String username) {
        User user = userRepository.getUserByName(username);
        return new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getCreatedAt(), user.getEmail(), user.getPhone());
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new BadRequestException(apiResponse);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User newUser, String username, UserPrincipal currentUser) {
        User user = userRepository.getUserByName(username);
        if (user.getId().equals(currentUser.getId())) {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setPhone(newUser.getPhone());
            return userRepository.save(user);
        }

        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update profile of: " + username);
        throw new UnauthorizedException(apiResponse);
    }
}
