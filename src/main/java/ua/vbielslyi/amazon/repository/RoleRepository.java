package ua.vbielslyi.amazon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vbielslyi.amazon.model.user.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(Role.ERole name);
}
