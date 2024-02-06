package ru.cft.template.сontroller.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.User;
import ru.cft.template.service.impl.UserService;
import ru.cft.template.сontroller.User.UserTypes.GetUsers;
import ru.cft.template.сontroller.User.UserTypes.UserPatch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService usersService;

    @GetMapping
    public List<GetUsers> getUsers() {
        return usersService.getUsers();
    }
    @GetMapping("/{id}")
    public GetUsers getUser(@PathVariable Long id) {
        return usersService.getUser(id);
    }
    @PostMapping
    public void save(@RequestBody User user) throws Exception {
        usersService.save(user);
    }
    @PatchMapping("/{id}")
    public GetUsers update(@PathVariable Long id, @RequestBody UserPatch userPatch) {
        return usersService.update(id, userPatch);
    }
}
