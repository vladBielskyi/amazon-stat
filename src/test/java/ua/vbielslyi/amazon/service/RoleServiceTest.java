package ua.vbielslyi.amazon.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.vbielslyi.amazon.model.user.Role;
import ua.vbielslyi.amazon.repository.RoleRepository;
import ua.vbielslyi.amazon.service.impl.RoleServiceImpl;

import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void testFindByName() {
        Role.ERole roleName = Role.ERole.ROLE_USER;
        Role role = new Role("234", roleName);

        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.findByName(roleName);

        assertTrue(result.isPresent());
        assertEquals(role, result.get());

        verify(roleRepository, times(1)).findByName(roleName);
    }
}
