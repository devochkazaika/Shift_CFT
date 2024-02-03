package ru.cft.template.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cft.template.dao.WalletRepository;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.impl.IWallet;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletService implements IWallet {
    @Autowired
    private final WalletRepository walletRepo;
    @Override
    public Optional<Wallet> findById(Long id){
        return walletRepo.findById(id);
    }

    @Override
    @Transactional
    public Wallet updateBalance(Long userId, Long amount) {
        walletRepo.hesoyam(userId, amount);
        return walletRepo.findById(userId).orElse(null);
    }
}
