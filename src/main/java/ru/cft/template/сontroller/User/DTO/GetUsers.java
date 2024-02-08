package ru.cft.template.сontroller.User.DTO;

import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDate;

@Data
public class GetUsers{
    private Long id;
    private String walletId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private Long phone;
    private LocalDate registrationDate;
    private LocalDate lastUpdateDate;
    private Integer age;
    public GetUsers(User user){
        id = user.getId();
        firstName = user.getFirstName();
        walletId = user.getWallet().getId().toString();
        lastName = user.getLastName();
        phone = user.getPhone();
        age = user.getAge();
        email = user.getEmail();
        fullName = firstName + " " + lastName;
        registrationDate = user.getRegistrationDate();
        lastUpdateDate = user.getLastUpdateDate();

    }

}
