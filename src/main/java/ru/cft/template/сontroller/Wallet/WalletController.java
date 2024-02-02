package ru.cft.template.сontroller.Wallet;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.User;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.UserService;
import ru.cft.template.service.WalletService;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.UserData;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping
public class WalletController {
    @Autowired
    private final WalletService walletRepo;

    @GetMapping("/wallet/bill/{id}")
    public GetWallet getBill(@PathVariable Long id) {
        Wallet wallet = walletRepo.findById(id).orElse(null);
        if (wallet != null) return new GetWallet(wallet);
        return null;
    }

    @PostMapping("/hesoyam")
    public GetWallet hesoyam(@RequestBody UserData userData) {
        walletRepo.updateBalance(userData.getUserId(), userData.getAmount());
        Wallet wallet = walletRepo.findById(userData.getUserId()).orElse(null);
        if (wallet != null) {
            return new GetWallet(wallet);
        }
        return null;
    }


}
