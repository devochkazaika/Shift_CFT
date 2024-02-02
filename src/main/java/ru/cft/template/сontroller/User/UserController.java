package ru.cft.template.сontroller.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.User;
import ru.cft.template.service.UserService;
import ru.cft.template.сontroller.User.UserTypes.Get.GetUsers;
import ru.cft.template.сontroller.User.UserTypes.Patch.UserPatch;
import ru.cft.template.сontroller.User.UserTypes.UserDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {
    @Autowired
    private final UserService usersRepo;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        List<User> users = usersRepo.findAll();
        List<UserDTO> userRequest = new ArrayList<UserDTO>();
        for (User user : users){
            userRequest.add(UserFabric.create("get/users", user));
        }
        return userRequest;
    }
    @GetMapping("/users/{id}")
    public UserDTO getUsers(@PathVariable Long id) {
        User user = usersRepo.findById(id).orElse(null);
        if (user != null) return new GetUsers(user);
        else return null;
    }
    @PostMapping("/users")
    public void save(@RequestBody User user) {
        usersRepo.save(user);
    }
    @PatchMapping("/users/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserPatch userPatch) {
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
