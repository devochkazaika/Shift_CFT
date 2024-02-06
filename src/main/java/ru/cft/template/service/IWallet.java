package ru.cft.template.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.cft.template.model.Wallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetTransfer;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.PostTransfer;
import ru.cft.template.сontroller.Wallet.WalletTypes.UserData;

import java.util.Optional;

public interface IWallet {
    GetWallet getBill(Long id);

    GetTransfer transfer(@RequestBody PostTransfer data) throws Exception;

    GetWallet updateBalance(Long userId, Long amount);
}
