package ru.cft.template.сontroller.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.User;
import ru.cft.template.service.UserService;
import ru.cft.template.сontroller.User.UserTypes.GetUsers;
import ru.cft.template.сontroller.User.UserTypes.UserPatch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {
    @Autowired
    private final UserService usersRepo;

    @GetMapping("/users")
    public List<GetUsers> getUsers() {
        List<User> users = usersRepo.findAll();
        List<GetUsers> userRequest = new ArrayList<GetUsers>();
        for (User user : users){
            userRequest.add(new GetUsers(user));
        }
        return userRequest;
    }
    @GetMapping("/users/{id}")
    public GetUsers getUsers(@PathVariable Long id) {
        User user = usersRepo.findById(id).orElse(null);
        if (user != null) return new GetUsers(user);
        else return null;
    }
    @PostMapping("/users")
    public void save(@RequestBody User user) {
        usersRepo.save(user);
    }
    @PatchMapping("/users/{id}")
    public GetUsers update(@PathVariable Long id, @RequestBody UserPatch userPatch) {
        User user = usersRepo.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(userPatch.getFirstName());
            user.setLastName(userPatch.getLastName());
            user.setFullName(userPatch.getFirstName() + " " + userPatch.getLastName());
            user.setEmail(userPatch.getEmail());
            user.setLastUpdateDate(LocalDate.now());
            usersRepo.update(id, user);
            return getUsers(id);
        }
        return null;
    }
}
