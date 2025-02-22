package com.fatiq.shoeapp.service.impl;

import com.fatiq.shoeapp.dto.RegisterRequest;
import com.fatiq.shoeapp.dto.UserDto;
import com.fatiq.shoeapp.entity.User;
import com.fatiq.shoeapp.exception.DuplicateResourceException;
import com.fatiq.shoeapp.exception.ResourceNotFoundException;
import com.fatiq.shoeapp.repository.UserRepository;
import com.fatiq.shoeapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

// When you register a user via /api/auth/register,
// the UserServiceImpl assigns only ROLE_USER by default:


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(RegisterRequest registerRequest) {
        logger.debug("Attempting to register user: {}", registerRequest.getUsername());
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            logger.warn("Username already taken: {}", registerRequest.getUsername());
            throw new DuplicateResourceException("Username '" + registerRequest.getUsername() + "' is already taken");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            logger.warn("Email already in use: {}", registerRequest.getEmail());
            throw new DuplicateResourceException("Email '" + registerRequest.getEmail() + "' is already in use");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setAddress(registerRequest.getAddress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRoles(new HashSet<>() {{ add("ROLE_USER"); }}); // Default role

        User savedUser = userRepository.save(user);
        logger.info("User registered successfully: {}", savedUser.getUsername());

        UserDto userDto = new UserDto();
        userDto.setUserId(savedUser.getUserId());
        userDto.setUsername(savedUser.getUsername());
        userDto.setEmail(savedUser.getEmail());
        userDto.setAddress(savedUser.getAddress());
        userDto.setPhoneNumber(savedUser.getPhoneNumber());
        return userDto;

    }

    @Override
    public UserDto getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.debug("Fetching current user: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> {
                            logger.error("User not found: {}", username);
                            return new ResourceNotFoundException("User '" + username + "' not found");
                        });

        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    @Override
    public UserDto promoteToAdmin(Long userId) {
        logger.debug("Promoting user with ID {} to admin", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new ResourceNotFoundException("User with ID " + userId + " not found");
                });
        user.getRoles().add("ROLE_ADMIN");
        User updatedUser = userRepository.save(user);
        logger.info("User {} promoted to admin", updatedUser.getUsername());
        return mapToDTO(updatedUser);
    }

    private UserDto mapToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }
}
