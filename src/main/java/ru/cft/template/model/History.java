//package ru.cft.template.model;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.cft.template.сontroller.Wallet.WalletTypes.PostTransfer;
//
//@Entity
//@Table(name = "history")
//@Data
//@NoArgsConstructor
//
//public class History {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JoinColumn(name="userid")
//    @OneToOne
//    private User user;
//
//    @JoinColumn(name="maintenancenumber")
//    @OneToOne
//    private Bill bill;
//
//    @Column(name="transactiondate")
//    private String transactionDate;
//
//    @Column(name="type")
//    private String type;
//
//}
