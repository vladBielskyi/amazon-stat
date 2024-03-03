package ua.vbielslyi.amazon.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.vbielslyi.amazon.model.user.User;
import ua.vbielslyi.amazon.repository.UserRepository;
import ua.vbielslyi.amazon.service.impl.UserServiceImpl;

import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void loadUserByUsername_UserFound() {
        User mockUser = new User("testuser", "password");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userService.loadUserByUsername("testuser");

        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty()); // Add assertions for authorities if needed
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistentuser"));
    }

    @Test
    void existsByUsername_UsernameExists() {
        when(userRepository.existsByUsername("existinguser")).thenReturn(true);

        boolean result = userService.existsByUsername("existinguser");

        assertTrue(result);
    }

    @Test
    void existsByUsername_UsernameDoesNotExist() {
        when(userRepository.existsByUsername("nonexistentuser")).thenReturn(false);

        boolean result = userService.existsByUsername("nonexistentuser");

        assertFalse(result);
    }

    @Test
    void saveUser() {
        User userToSave = new User("newuser", "password");
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        User savedUser = userService.save(userToSave);

        assertNotNull(savedUser);
        assertEquals("newuser", savedUser.getUsername());
        assertEquals("password", savedUser.getPassword());
    }
}
