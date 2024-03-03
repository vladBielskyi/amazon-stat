package ua.vbielslyi.amazon.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.vbielslyi.amazon.dto.request.LoginRequest;
import ua.vbielslyi.amazon.dto.request.SignupRequest;
import ua.vbielslyi.amazon.dto.response.JwtResponse;
import ua.vbielslyi.amazon.security.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authRestController;

    @Test
    void testSignIn() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        JwtResponse jwtResponse = new JwtResponse("token");

        when(authService.signIn(loginRequest)).thenReturn(jwtResponse);

        ResponseEntity<?> responseEntity = authRestController.signIn(loginRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtResponse, responseEntity.getBody());

        verify(authService, times(1)).signIn(loginRequest);
    }

    @Test
    void testSignUp() {
        SignupRequest signUpRequest = new SignupRequest("username", "password");

        ResponseEntity<?> responseEntity = authRestController.signUp(signUpRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        verify(authService, times(1)).signUp(signUpRequest);
    }
}
