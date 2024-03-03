package ua.vbielslyi.amazon.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vbielslyi.amazon.dto.request.LoginRequest;
import ua.vbielslyi.amazon.dto.request.SignupRequest;
import ua.vbielslyi.amazon.dto.response.JwtResponse;
import ua.vbielslyi.amazon.exception.UserAlreadyExistException;
import ua.vbielslyi.amazon.model.user.Role;
import ua.vbielslyi.amazon.model.user.User;
import ua.vbielslyi.amazon.security.jwt.JwtUtils;
import ua.vbielslyi.amazon.security.service.AuthService;
import ua.vbielslyi.amazon.service.RoleService;
import ua.vbielslyi.amazon.service.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtResponse(jwtUtils.generateJwtToken(authentication));
    }

    @Override
    public void signUp(SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistException("Username is already taken");
        }

        User user = new User(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()));

        Role role = roleService.findByName(Role.ERole.ROLE_USER).get();
        user.setRoles(Set.of(role));
        userService.save(user);
    }
}
