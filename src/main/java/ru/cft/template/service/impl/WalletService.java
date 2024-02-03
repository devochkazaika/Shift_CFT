package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.IWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletService implements IWallet {
    @Autowired
    private final WalletRepository walletRepo;
    @Override
    public GetWallet getBill(Long id){
        Wallet wallet = walletRepo.findById(id).orElse(null);
        if (wallet != null) return new GetWallet(wallet);
        return null;
    }

    @Override
    @Transactional
    public GetWallet updateBalance(Long userId, Long amount) {
        walletRepo.hesoyam(userId, amount);
        Wallet wallet = walletRepo.findById(userId).orElse(null);
        if (wallet != null) {
            return new GetWallet(wallet);
        }
        else return null;

    }
}
