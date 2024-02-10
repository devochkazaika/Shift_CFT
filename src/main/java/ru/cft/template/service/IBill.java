package ru.cft.template.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.cft.template.сontroller.Bill.DTO.GetBill;
import ru.cft.template.сontroller.Wallet.WalletTypes.*;

import java.util.List;

public interface IBill {
    GetWallet getBill(Long id);

    List<GetBill> getBillS(Long userId);

    GetMaintenance maintenancePost(PostMaintenance maintenance) throws Exception;
}
