package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Data;
import ru.cft.template.model.Wallet;

@Data
public class GetTransfer {
    private String id;
    private String userId;
    private WalletInfo wallet;

    public GetTransfer setId(String id){
        this.id = id;
        return this;}
    public GetTransfer setUserId(String id){
        this.userId = id;
        return this;
    }
    public GetTransfer setWallet(String id, Long amount){
        wallet = new WalletInfo(id, amount);
        return this;
    }
}
