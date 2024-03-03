package ua.vbielslyi.amazon.service;

import ua.vbielslyi.amazon.model.user.User;

public interface UserService {

    boolean existsByUsername(String username);

    User save(User user);
}
