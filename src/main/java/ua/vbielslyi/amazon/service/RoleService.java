package ua.vbielslyi.amazon.service;

import ua.vbielslyi.amazon.model.user.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(Role.ERole name);
}
