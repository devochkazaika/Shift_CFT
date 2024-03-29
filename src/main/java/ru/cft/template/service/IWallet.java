package ru.cft.template.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.cft.template.model.Wallet;
import ru.cft.template.сontroller.Bill.DTO.GetBill;
import ru.cft.template.сontroller.Wallet.WalletTypes.*;

import java.util.List;
import java.util.Optional;

public interface IWallet {

    GetTransfer transfer(@RequestBody PostTransfer data) throws Exception;

    GetWallet updateBalance(Long userId, Long amount) throws Exception;

}
