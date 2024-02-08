package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Data;

@Data
public class PostMaintenance {
    private String userId;
    private Long phone;
    private Long amount;
    private String comment;
}
