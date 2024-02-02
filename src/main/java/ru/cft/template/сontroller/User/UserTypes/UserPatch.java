package ru.cft.template.сontroller.User.UserTypes.Patch;

import lombok.Data;
import ru.cft.template.сontroller.User.UserTypes.UserDTO;

@Data
public class UserPatch extends UserDTO {
    private String firstName;
    private String lastName;
    private String email;
}
