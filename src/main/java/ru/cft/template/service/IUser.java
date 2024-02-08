package ru.cft.template.service;

import ru.cft.template.model.User;
import ru.cft.template.сontroller.User.DTO.GetUsers;
import ru.cft.template.сontroller.User.DTO.UserPatch;

import java.util.List;

public interface IUser {
    GetUsers getUser(Long id);
    List<GetUsers> getUsers();
    void save(User user) throws Exception;
    GetUsers update(Long id, UserPatch userPatch);
    User findByPhone(Long phone);
}
