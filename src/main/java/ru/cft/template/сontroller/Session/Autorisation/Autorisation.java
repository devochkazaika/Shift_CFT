package ru.cft.template.сontroller.Session.Autorisation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Autorisation {
    private Long id;
    private Long userId;
    private String token;
    private LocalDate expirationTime;
}
