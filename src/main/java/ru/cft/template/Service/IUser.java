package ru.cft.template.Service;

import ru.cft.template.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUser {
    Optional<User> findById(Long id);
    List<User> findAll();
}
