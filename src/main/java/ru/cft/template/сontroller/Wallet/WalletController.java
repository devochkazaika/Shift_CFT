package ru.cft.template.сontroller.Wallet;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.impl.WalletService;
import ru.cft.template.сontroller.Wallet.WalletTypes.GetWallet;
import ru.cft.template.сontroller.Wallet.WalletTypes.UserData;

@RestController
@AllArgsConstructor
@RequestMapping
public class WalletController {
    @Autowired
    private final WalletService walletService;

    @GetMapping("/wallet/bill/{id}/current")
    public GetWallet getBill(@PathVariable Long id) {
        return walletService.getBill(id);
    }

    @PostMapping("/hesoyam")
    public GetWallet hesoyam(@RequestBody UserData userData) {
        return walletService.updateBalance(userData.getUserId(), userData.getAmount());
    }


}
