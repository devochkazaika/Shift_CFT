package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne
    @JoinColumn(name="othertwo")
    private User userTwo;

    @Column(name="amount")
    private Long amount;

    public void setAmount(Long amount){
        this.amount = amount;
        this.amountRemains = amount;
    }
    public Bill amount(Long amount){
        setAmount(amount);
        return this;
    }

    @Column(name="type")
    private String type;

    @Column(name = "maintenancenumber")
    private Long maintenanceNumber;

    @Column(name="status")
    private String status;

    @Column(name = "amountremains")
    private long amountRemains;

    @Transient
    private LocalDate transactionDate;
}
