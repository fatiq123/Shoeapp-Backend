package com.fatiq.shoeapp.controller;

import com.fatiq.shoeapp.dto.ApiResponse;
import com.fatiq.shoeapp.dto.UserDto;
import com.fatiq.shoeapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/promote-to-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> promoteToAdmin(@PathVariable Long userId) {
        logger.info("Request to promote user ID {} to admin", userId);
        UserDto userDTO = userService.promoteToAdmin(userId);
        return ResponseEntity.ok(ApiResponse.success(userDTO, "User promoted to admin successfully"));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> getCurrentUser() {
        logger.info("Fetching current user details");
        UserDto userDTO = userService.getCurrentUser();
        return ResponseEntity.ok(ApiResponse.success(userDTO, "Current user retrieved successfully"));
    }
}