package com.fatiq.shoeapp.service;

import com.fatiq.shoeapp.dto.RegisterRequest;
import com.fatiq.shoeapp.dto.UserDto;

public interface UserService {

    UserDto registerUser(RegisterRequest registerRequest);
    UserDto getCurrentUser();
    UserDto promoteToAdmin(Long userId); // New method
}
