package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.cft.template.model.Bill;
import ru.cft.template.model.User;
import ru.cft.template.repository.BillRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.IBill;
import ru.cft.template.service.IWallet;
import ru.cft.template.сontroller.Bill.DTO.GetBill;
import ru.cft.template.сontroller.Wallet.WalletTypes.*;

import java.nio.file.WatchKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.cft.template.сontroller.Wallet.WalletTypes.GetTransfer.*;

@Service
@AllArgsConstructor
public class BillService implements IBill {
    @Autowired
    private final WalletRepository walletRepo;
    private final BillRepository billRepo;
    private final UserRepository userRepo;

    @Override
    public GetWallet getBill(Long id){
        Bill bill = billRepo.findById(id).orElse(null);
        if (bill != null) return new GetWallet(bill);
        return null;
    }


    @Override
    public List<GetBill> getBillS(Long userId){
        List<Bill> bills = billRepo.getBills(userId);
        List<GetBill> getReq = new ArrayList<>();
        for (Bill bill : bills){
            getReq.add(new GetBill(bill));
        }
        return getReq;
    }

    @Override
    public GetMaintenance maintenancePost(PostMaintenance maintenance) throws Exception{
        User userSender = userRepo.findById(Long.parseLong(maintenance.getUserId()))
                .orElse(null);
        User userReceiver = userRepo.findByPhone(maintenance.getPhone())
                .orElse(null);

        if (userSender==null || userReceiver == null){
            throw new Exception("Нет такого получателя или отправителя");
        }

        Bill billSender = Bill.builder()
                .user(userSender)
                .amount(maintenance.getAmount())
                .type("outbound").build();
        Bill billReceiver = Bill.builder()
                .status("noGet")
                .user(userReceiver)
                .amount(maintenance.getAmount())
                .type("inbound").build();

        billRepo.save(billSender);
        billRepo.save(billReceiver);

        GetMaintenance get = GetMaintenance.builder()
                .id(userReceiver.getId())
                .status("enum")
                .maintenanceNumber(billSender.getId())
                .build();
        return get;
    }
}
