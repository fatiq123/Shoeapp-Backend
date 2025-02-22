package com.fatiq.shoeapp.service;

import com.fatiq.shoeapp.dto.JwtResponse;
import com.fatiq.shoeapp.dto.LoginRequest;

public interface AuthService {

    JwtResponse login(LoginRequest loginRequest);

}
