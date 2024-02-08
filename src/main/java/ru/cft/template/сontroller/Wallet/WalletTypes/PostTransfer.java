package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Data;

@Data
public class PostTransfer {
    private String userId;
    private Long receiverPhone;
    private Long maintenanceNumber;
    private Long amount;
}
