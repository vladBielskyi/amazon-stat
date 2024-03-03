package ua.vbielslyi.amazon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vbielslyi.amazon.model.user.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
