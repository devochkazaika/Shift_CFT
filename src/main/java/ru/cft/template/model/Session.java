package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "userid")
    private Long userId;


    @Column(name = "expirationtime")
    private LocalDate expirationTime;

    @Column(name = "active")
    private Integer active;
}
