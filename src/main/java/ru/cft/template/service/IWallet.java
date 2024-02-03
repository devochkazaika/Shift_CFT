package ru.cft.template.service.impl;

import ru.cft.template.model.Wallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.UserData;

import java.util.Optional;

public interface IWallet {
    Optional<Wallet> findById(Long id);

//    void update(Long id, Long amount);

    Wallet updateBalance(Long userId, Long amount);
}
