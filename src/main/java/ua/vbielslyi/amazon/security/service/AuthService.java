package ua.vbielslyi.amazon.security.service;

import ua.vbielslyi.amazon.dto.request.LoginRequest;
import ua.vbielslyi.amazon.dto.request.SignupRequest;
import ua.vbielslyi.amazon.dto.response.JwtResponse;

public interface AuthService {

    JwtResponse signIn(LoginRequest loginRequest);

    void signUp(SignupRequest signUpRequest);
}
