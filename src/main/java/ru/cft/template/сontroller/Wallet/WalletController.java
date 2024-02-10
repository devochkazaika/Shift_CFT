package ru.cft.template.сontroller.Wallet;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.impl.BillService;
import ru.cft.template.service.impl.WalletService;
import ru.cft.template.сontroller.Bill.DTO.GetBill;
import ru.cft.template.сontroller.Wallet.WalletTypes.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class WalletController {
    @Autowired
    private final WalletService walletService;
    private final BillService billService;

    @GetMapping("/wallet/bill/{id}")
    public GetWallet getBill(@PathVariable Long id) {
        return billService.getBill(id);
    }

    @PostMapping("/hesoyam")
    public GetWallet hesoyam(@RequestBody UserData userData) throws Exception {
        return walletService.updateBalance(userData.getUserId(), userData.getAmount());
    }

    @Modifying
    @PostMapping("/transfers")
    public GetTransfer transfer(@RequestBody PostTransfer data) throws Exception {
        return walletService.transfer(data);
    }

    @GetMapping("/maintenance/{userId}")
    public List<GetBill> maintenance(@PathVariable Long userId){
        return billService.getBillS(userId);
    }

    @PostMapping("/maintenance")
    public GetMaintenance maintenancePost(@RequestBody PostMaintenance maintenance) throws Exception {
        return billService.maintenancePost(maintenance);
    }

}
