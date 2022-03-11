package com.demo.restapi.service;

import com.demo.restapi.model.User;
import com.demo.restapi.payload.UserIdentityAvailability;
import com.demo.restapi.payload.UserProfile;
import com.demo.restapi.payload.UserSummary;
import com.demo.restapi.security.UserPrincipal;

public interface UserService {

    UserSummary getCurrentUser(UserPrincipal currentUser);

    UserIdentityAvailability checkUsernameAvailability(String username);

    UserIdentityAvailability checkEmailAvailability(String email);

    UserProfile getUserProfile(String username);

    User addUser(User user);

    User updateUser(User newUser, String username, UserPrincipal currentUser);
}
