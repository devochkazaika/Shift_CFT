package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "lastupdate")
    private LocalDate lastUpdate;
}
