package ru.cft.template.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.Models.User;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUser{
    private final UserRepository userRepo;
    @Override
    public Optional<User> findById(Long id) {

        return userRepo.findById(id);
    }
    @Override
    public List<User> findAll() {

        return userRepo.findAll();
    }
}
