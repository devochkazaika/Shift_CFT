package ru.cft.template.сontroller.Wallet;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.WalletService;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.UserData;

@RestController
@AllArgsConstructor
@RequestMapping
public class WalletController {
    @Autowired
    private final WalletService walletRepo;

    @GetMapping("/wallet/bill/{id}/current")
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
