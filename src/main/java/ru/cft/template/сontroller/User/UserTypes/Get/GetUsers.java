package ru.cft.template.сontroller.User.UserTypes.Get;

import lombok.Data;
import ru.cft.template.model.User;
import ru.cft.template.сontroller.User.UserTypes.UserDTO;

import java.time.LocalDate;

@Data
public class GetUsers extends UserDTO {
    private Long id;
    private Long walletId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private Integer phone;
    private LocalDate registrationDate;
    private LocalDate lastUpdateDate;
    private Integer age;
    public GetUsers(User user){
        id = user.getId();
        firstName = user.getFirstName();
        walletId = user.getWallet().getId();
        lastName = user.getLastName();
        phone = user.getPhone();
        age = user.getAge();
        email = user.getEmail();
        fullName = firstName + lastName;
        registrationDate = user.getRegistrationDate();
        lastUpdateDate = user.getLastUpdateDate();

    }

}
