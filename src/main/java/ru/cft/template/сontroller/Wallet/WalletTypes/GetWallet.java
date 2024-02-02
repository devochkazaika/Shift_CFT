package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Data;
import ru.cft.template.model.Wallet;

import java.time.LocalDate;

@Data
public class GetWallet {
    private Long id;
    private Long amount;
    private LocalDate lastUpdate;
    public GetWallet(Wallet wallet){
        id = wallet.getId();
        amount = wallet.getAmount();
        lastUpdate = wallet.getLastUpdate();
    }
}
