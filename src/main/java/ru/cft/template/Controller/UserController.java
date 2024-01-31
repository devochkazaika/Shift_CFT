package ru.cft.template.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.Models.User;
import ru.cft.template.Service.UserRepository;
import ru.cft.template.Service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {
    @Autowired
    private final UserService usersRepo;

    @GetMapping("/users")
    public List<User> getUsers() {
        return usersRepo.findAll();
    }
    @GetMapping("/users/{id}")
    public Optional<User> getUsers(@PathVariable Long id) {
        return usersRepo.findById(id);
    }
    @PostMapping("/users")
    public void save(@RequestBody User user) {
        usersRepo.save(user);
    }
    @PatchMapping("/users/{id}")
    public void update(@PathVariable Long id, @RequestBody User user) {
        usersRepo.update(id, user);
    }
}
