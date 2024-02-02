package ru.cft.template.сontroller.Session.Autorisation;

import lombok.Data;

import java.time.LocalDate;

//Пока без защиты
@Data
public class Autorisation {
    private Long id;
    private Long userId;
    private String token;
    private LocalDate expirationTime;
}
