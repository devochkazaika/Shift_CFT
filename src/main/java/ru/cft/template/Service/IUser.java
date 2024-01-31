package ru.cft.template.Service;

import org.springframework.data.jpa.repository.Query;
import ru.cft.template.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUser {
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User user);
    void update(Long id, User user);
}
