package ru.cft.template.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "walletid")
    private String walletId;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Integer phone;
    @Temporal(TemporalType.DATE)
    @Column(name="registrationdate")
    private Date registrationDate;

    @Temporal(TemporalType.DATE)
    @Column(name="lastupdatedate")
    private Date lastUpdateDate;

    @Column(name = "age")
    private Integer age;
}
