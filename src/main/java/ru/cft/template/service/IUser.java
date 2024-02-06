package ru.cft.template.service;

import org.springframework.web.bind.annotation.PathVariable;
import ru.cft.template.model.User;
import ru.cft.template.сontroller.User.UserTypes.GetUsers;
import ru.cft.template.сontroller.User.UserTypes.UserPatch;

import java.util.List;
import java.util.Optional;

public interface IUser {
    GetUsers getUser(Long id);
    List<GetUsers> getUsers();
    void save(User user) throws Exception;
    GetUsers update(Long id, UserPatch userPatch);
    User findByPhone(Long phone);
}
