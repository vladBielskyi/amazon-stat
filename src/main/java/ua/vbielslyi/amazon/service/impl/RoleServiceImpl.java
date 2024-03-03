package ua.vbielslyi.amazon.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vbielslyi.amazon.model.user.Role;
import ua.vbielslyi.amazon.repository.RoleRepository;
import ua.vbielslyi.amazon.service.RoleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(Role.ERole name) {
        return roleRepository.findByName(name);
    }
}
