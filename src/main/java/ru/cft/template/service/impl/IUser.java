package ru.cft.template.service.impl;

import ru.cft.template.model.User;

import java.util.List;
import java.util.Optional;

public interface IUser {
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User user);
    void update(Long id, User user);
    User findByPhone(Integer phone);
}
