package ua.vbielslyi.amazon.security.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.vbielslyi.amazon.dto.request.LoginRequest;
import ua.vbielslyi.amazon.dto.request.SignupRequest;
import ua.vbielslyi.amazon.dto.response.JwtResponse;
import ua.vbielslyi.amazon.exception.UserAlreadyExistException;
import ua.vbielslyi.amazon.model.user.Role;
import ua.vbielslyi.amazon.security.jwt.JwtUtils;
import ua.vbielslyi.amazon.security.service.impl.AuthServiceImpl;
import ua.vbielslyi.amazon.service.RoleService;
import ua.vbielslyi.amazon.service.UserService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testSignIn() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("fakeToken");

        JwtResponse jwtResponse = authService.signIn(loginRequest);

        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtUtils, times(1)).generateJwtToken(authentication);
    }

    @Test
    void testSignUp() {
        SignupRequest signUpRequest = new SignupRequest("newUser", "password");
        Role role = new Role("234", Role.ERole.ROLE_USER);
        when(roleService.findByName(Role.ERole.ROLE_USER)).thenReturn(java.util.Optional.of(role));
        when(userService.existsByUsername("newUser")).thenReturn(false);

        authService.signUp(signUpRequest);

        verify(roleService, times(1)).findByName(Role.ERole.ROLE_USER);
        verify(userService, times(1)).existsByUsername("newUser");
        verify(passwordEncoder, times(1)).encode("password");
        verify(roleService, times(1)).findByName(Role.ERole.ROLE_USER);
        verify(userService, times(1)).save(any());
    }

    @Test
    void testSignUpUsernameTaken() {
        SignupRequest signUpRequest = new SignupRequest("existingUser", "password");
        when(userService.existsByUsername("existingUser")).thenReturn(true);

        assertThrows(UserAlreadyExistException.class, () -> authService.signUp(signUpRequest));

        verify(userService, never()).save(any());
    }
}
