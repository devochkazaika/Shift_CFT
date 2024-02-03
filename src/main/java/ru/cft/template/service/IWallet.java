package ru.cft.template.service;

import ru.cft.template.model.Wallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.UserData;

import java.util.Optional;

public interface IWallet {
    GetWallet getBill(Long id);

//    void update(Long id, Long amount);

    GetWallet updateBalance(Long userId, Long amount);
}
