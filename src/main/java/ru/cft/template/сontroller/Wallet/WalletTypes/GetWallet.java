package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Data;
import ru.cft.template.model.Bill;
import ru.cft.template.model.Wallet;

import java.time.LocalDate;

@Data
public class GetWallet {
    private Long userid;
    private Long amount;
    private Long billId;
    public GetWallet(Bill bill){
        userid = bill.getUser().getId();
        amount = bill.getAmount();
        billId = bill.getId();
    }
}
