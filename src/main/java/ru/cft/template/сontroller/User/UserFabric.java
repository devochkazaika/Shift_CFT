package ru.cft.template.сontroller.User;

import ru.cft.template.model.User;
import ru.cft.template.сontroller.User.UserTypes.Get.GetUsers;
import ru.cft.template.сontroller.User.UserTypes.UserDTO;

public class UserFabric {
    public static UserDTO create(String arg, User user) {
        if (arg.equals("get/users")) {
            return new GetUsers(user);
        }
        return null;
    }
}
