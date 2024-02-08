package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Data;

@Data
public class WalletInfo {
    private String id;
    private Long amount;
    public WalletInfo(String id, Long amount){
        this.id = id;
        this.amount = amount;
    }
}
