package ru.cft.template.сontroller.User.DTO;

import lombok.Data;

@Data
public class UserPatch {
    private String firstName;
    private String lastName;
    private String email;
}
