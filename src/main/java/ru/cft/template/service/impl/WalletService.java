package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.IWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetTransfer;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.PostTransfer;

import java.util.Optional;

import static ru.cft.template.сontroller.Wallet.WalletTypes.GetTransfer.*;

@Service
@AllArgsConstructor
public class WalletService implements IWallet {
    @Autowired
    private final WalletRepository walletRepo;
    @Override
    public GetWallet getBill(Long id){
        Wallet wallet = walletRepo.findByUserId(id).orElse(null);
        if (wallet != null) return new GetWallet(wallet);
        return null;
    }

    @Override
    @Transactional
    public GetWallet updateBalance(Long userId, Long amount) {
        walletRepo.hesoyam(userId, amount);
        Wallet wallet = walletRepo.findByUserId(userId).orElse(null);
        if (wallet != null) {
            return new GetWallet(wallet);
        }
        else return null;

    }
    @Transactional
    private void changeBalance(Wallet wallet, Long sum){
        wallet.setAmount(wallet.getAmount() + sum);
//        System.out.println(wallet.getAmount());
    }

    @Override
    @Transactional
    public GetTransfer transfer(@RequestBody PostTransfer data) throws Exception{
        Wallet walletSender = walletRepo.findByUserId(Long.parseLong(data.getUserId())).orElse(null);
        Wallet walletReceiver = walletRepo.findByPhone(data.getReceiverPhone()).orElse(null);

        if (walletSender == null || walletReceiver == null) {
            throw new Exception("Неправильный id отправителя или неправильный номер получателя");
        }
        if (walletSender.getAmount() - data.getAmount() < 0){
            throw new Exception("недостаточно средств");
        }
        changeBalance(walletSender, (-1) * data.getAmount());
        changeBalance(walletReceiver, data.getAmount());
        return new GetTransfer()
                .setUserId(data.getUserId())
                .setWallet(walletReceiver.getId().toString(), data.getAmount());
    }
}
