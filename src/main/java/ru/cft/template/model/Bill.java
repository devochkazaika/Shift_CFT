package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bills")
@Builder
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="userid")
    private User user;

    @Column(name="amount")
    private Long amount;

    @Column(name="type")
    private String type;

    @Column(name = "maintenancenumber")
    private Long maintenanceNumber;

    @Transient
    private Boolean status;

    @Transient
    private LocalDate transactionDate;
}
